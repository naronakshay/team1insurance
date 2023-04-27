package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Entity.Plan;
import insurance.premium.backend.Entity.Policy;
import insurance.premium.backend.Repo.MemberRepo;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.*;

import java.util.Date;
import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private KieSession session;

    @Autowired
    private MemberRepo memberRepo;



    //calculate age of a person from the date of birth
    public static int calculateAge(Date dateOfBirth) {
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }


    // to check whether a user belong to tier_1 city or not
    public static boolean isTier1City(String city_name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isTier1City = false;
        try {



            // Establish a connection to the MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lookup","root","");
            stmt = conn.prepareStatement("SELECT tier1_city FROM city WHERE city_name = ?");

            stmt.setString(1, city_name);
            //execute the query and stores the result in rs
            rs = stmt.executeQuery();
            if (rs.next()) {

                isTier1City = rs.getBoolean("tier1_city");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isTier1City;
    }



    //calculate the additional amount a user need to pay extra for pre-exsisting illness one by one
    public static double diseasePremium(String disease_name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double diseasePremium=0.0;
        try {
            // Establish a connection to the MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lookup","root","");
            stmt = conn.prepareStatement("SELECT additional_premium FROM disease WHERE disease_name = ?");
            stmt.setString(1, disease_name);
            //execite the query
            rs = stmt.executeQuery();
            if (rs.next()) {
                diseasePremium = rs.getDouble("additional_premium");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return diseasePremium;
    }





    //calculate and return the additional premium for  user for all dieases
    public double illnessCheck(String illnessDetails) {
        Double illnessPremium = 0.0;
        String[] illnessArray = illnessDetails.split(",");
        for (int i = 0; i < illnessArray.length; i++){

            double premium=diseasePremium(illnessArray[i].trim());

            illnessPremium+=premium;

        }
        return illnessPremium;
    }





    //calculate the basic preimum amount for a person
    public Policy calculatePremium(Member member) {

        Policy p=new Policy();
        //sets the required variables to calculate premium for a user in policy
        p.setIstobaccoUser(member.getTobaccoUser());
        p.setGender(member.getGender());
        p.setAge(calculateAge(member.getDob()));
        p.setIstier1City(isTier1City(member.getCity()));
        p.setIllnesspremium(illnessCheck(member.getIllnessDetails()));


        //insert object p into session and fire the rules in the drl file
        session.insert(p);
        session.fireAllRules();

        //return the object after calculating the premium
        return p;
    }



 


    //calculate the basic preimum amount for a person


    // calculate the additional premium for each type  of plans according to plan type
    public double calculateAdditionalPremium(String planType, double basicPremium){


        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double additionalPremium = 0;

        try {
            // Establish a connection to the MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_registration_db", "root", "");

            // Prepare the SQL statement to retrieve the additional premium for the given plan type
            stmt = conn.prepareStatement("SELECT additional_premium FROM plans WHERE plan_type = ?");
            stmt.setString(1, planType);

            // Execute the SQL query and retrieve the additional premium
            rs = stmt.executeQuery();
            if (rs.next()) {
                additionalPremium = rs.getDouble("additional_premium");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return additionalPremium;
    }








    // Return the  types of plans that can be provied to  users based on calculated premium
    public List<Plan> calculatePlans(double premium){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double basicPremium = premium;
        //create plan list to add diffrernt plans
        List<Plan> plans = new ArrayList<>();

        try {
            // Establish a connection to the MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_registration_db","root","");
            stmt = conn.prepareStatement("SELECT plan_id, plan_type,plan_details, coverage,cashless_hospitals FROM plans");
            //execute the query and store result in rs
            rs = stmt.executeQuery();


            while (rs.next()) {
                //create a new object plan to store plan details
                Plan plan = new Plan();
                plan.setPlan_id(rs.getInt("plan_id"));
                plan.setPlan_type(rs.getString("plan_type"));
                plan.setCoverage(rs.getInt("coverage"));
                plan.setPlan_details(rs.getString("plan_details"));
                plan.setCashless_hospitals(rs.getInt("cashless_hospitals"));
                //calculate the additional premium and add it to basic premium and store in plan object
                double additionalPremium = calculateAdditionalPremium(plan.getPlan_type(), basicPremium);
                double finalPremium = basicPremium + additionalPremium;
                plan.setFinalPremium(finalPremium);
                plan.setMonthlyPremium((int)plan.getFinalPremium()/12);

                //store each plan in plans list
                plans.add(plan);



            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  plans;


    }

}


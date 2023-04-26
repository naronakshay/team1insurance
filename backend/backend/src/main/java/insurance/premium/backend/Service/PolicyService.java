package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Entity.Policy;
import insurance.premium.backend.Repo.MemberRepo;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

@Service
public class PolicyService {

    @Autowired
    private KieSession session;

    @Autowired
    private MemberRepo memberRepo;



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


    public static boolean isTier1City(String city_name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isTier1City = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_registration_db","root","");
            stmt = conn.prepareStatement("SELECT tier1_city FROM city WHERE city_name = ?");
            stmt.setString(1, city_name);
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

    public static double diseasePremium(String disease_name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double diseasePremium=0.0;
        try {
// Establish a connection to the MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_registration_db","root","");
            stmt = conn.prepareStatement("SELECT additional_premium FROM disease WHERE disease_name = ?");
            stmt.setString(1, disease_name);
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



    /*public static Boolean[] illnessCheckArray(String illnessDetails) {
        Boolean isDiabetic = false;
        Boolean isHypertensive = false;

        String[] illnessArray = illnessDetails.split(",");

        for (String illness : illnessArray) {
            if (illness.equalsIgnoreCase("Diabetes")) {
                isDiabetic = true;
            }
            if (illness.equalsIgnoreCase("Hypertension")) {
                isHypertensive = true;

            }
        }
            return new Boolean[]{isDiabetic,isHypertensive};


    }*/

    public double illnessCheck(String illnessDetails) {
        Double illnessPremium = 0.0;
        String[] illnessArray = illnessDetails.split(",");
        for (int i = 0; i < illnessArray.length; i++){
            System.out.println(illnessArray[i]);
            double premium=diseasePremium(illnessArray[i].trim());
            System.out.println(premium);
            illnessPremium+=premium;

        }






        return illnessPremium;
    }


    public Policy calculatePremium(Member member) {
        Policy p=new Policy();
        p.setIstobaccoUser(member.getTobaccoUser());
        p.setGender(member.getGender());
        p.setAge(calculateAge(member.getDob()));
        p.setIstier1City(isTier1City(member.getCity()));
        p.setIllnessPremium(illnessCheck(member.getIllnessDetails()));


        session.insert(p);
        session.fireAllRules();


        return p;

    }
}

package insurance.premium.backend.Service;
import insurance.premium.backend.Entity.*;
import insurance.premium.backend.Repo.CityRepo;
import insurance.premium.backend.Entity.Disease;
import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Entity.Plan;
import insurance.premium.backend.Entity.Policy;
import insurance.premium.backend.Repo.DiseaseRepo;
import insurance.premium.backend.Repo.MemberRepo;
import insurance.premium.backend.Repo.PlansRepo;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private KieSession session;

    @Autowired
    private PlansRepo plansRepo;


    @Autowired
    private DiseaseRepo diseaseRepo;


    @Autowired
    private CityRepo cityRepo;

    //calculate age of a person from the date of birth
    public static int calculateAge(Date dateOfBirth) throws IllegalArgumentException {
        try {
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
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date of birth", e);
        }
    }


    // to check whether a user belong to tier_1 city or not
    public boolean isTier1City(String city_name) {

        City city = cityRepo.findByCityName(city_name);
        return city.isTier_1();
    }


    //calculate the additional amount a user need to pay extra for pre-existing illness one by one
    public double diseasePremium(String disease_name) {
        double diseasePremium = 0.0;
        Disease disease = diseaseRepo.findByDiseaseName(disease_name);
        try {
            if (disease != null) {
                diseasePremium = disease.getAdditional_premium();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diseasePremium;
    }


    //calculate and return the additional premium for  user for all disease
    public double illnessCheck(String illnessDetails) {
        Double illnessPremium = 0.0;
        String[] illnessArray = illnessDetails.split(",");
        for (int i = 0; i < illnessArray.length; i++) {
            try {
                double premium = diseasePremium(illnessArray[i].trim());
                illnessPremium += premium;
            } catch (Exception e) {
                e.printStackTrace();
                return 0.0;
            }
        }
        return illnessPremium;
    }


    //calculate the basic premium amount for a person
    public Policy calculatePremium(Member member) {

        Policy p = new Policy();
        //sets the required variables to calculate premium for a user in policy
        try {
            p.setIstobaccoUser(member.getIsTobaccoUser());
            p.setGender(member.getGender());
            p.setAge(calculateAge(member.getDob()));
            p.setIstier1City(isTier1City(member.getCity()));
            p.setIllnesspremium(illnessCheck(member.getIllnessDetails()));
            //insert object p into session and fire the rules in the drl file
            session.insert(p);
            session.fireAllRules();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return the object after calculating the premium
        return p;
    }


    // calculate the additional premium for each type  of plans according to plan type
    public double calculateAdditionalPremium(String planType) {

        double additionalPremium = 0;

        try {
            Plan plan = plansRepo.findByPlanType(planType);
            if (plan != null) {
                additionalPremium = plan.getAdditional_premium();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return additionalPremium;
    }


    // Return the  types of plans that can be provied to  users based on calculated premium
    public List<Plan> calculatePlans(double premium) {

        double basicPremium = premium;
        //create plan list to add all plans in database
        List<Plan> plans = plansRepo.findAll();
        //take each the plans and calculate and add the final premium for all plans into the plan entity
        try {
            for (Plan plan : plans) {
                double additionalPremium = calculateAdditionalPremium(plan.getPlanType());
                double finalPremium = basicPremium + additionalPremium;
                plan.setFinalPremium(finalPremium);
                plan.setMonthlyPremium((int) plan.getFinalPremium() / 12);
            }
        } catch (Exception e) {
            e.printStackTrace();
            plans = new ArrayList<>();
        }
        return plans;
    }
}

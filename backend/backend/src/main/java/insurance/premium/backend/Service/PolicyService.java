package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Entity.Policy;
import insurance.premium.backend.Repo.MemberRepo;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Service
public class PolicyService {

    private KieSession kieSession;

    @Autowired
    private MemberRepo memberRepo;


    public void calculatePremium(String email) {
        Member member = memberRepo.findByEmail(email);
        Policy policy = new Policy();
        if (member != null) {
            policy.setGender(member.getGender());
            policy.setIstobaccoUser(member.getTobaccoUser());

            Date dob = member.getDob();

            if (dob != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dob);
                LocalDate dob1 = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
                LocalDate now = LocalDate.now();
                int age = Period.between(dob1, now).getYears();
                policy.setAge(age);
            }
            // Set other member details as needed
        }

        kieSession.insert(policy);
        kieSession.fireAllRules();


        double premium = 0.0;
        if (policy.getPremium() != 0) {
            premium = policy.getPremium();
        }
        policy.setPremium(premium);
    }

}

package insurance.premium.backend.Service;

import insurance.premium.backend.Controller.MemberController;
import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Entity.MemberPremium;
import insurance.premium.backend.Repo.MemberPremiumRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;

public class MemberPremiumService {

    public int calculateAge(Member member) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(member.getDob(), currentDate).getYears();
    }
    MemberController memberController;
    public double calculatePremium(Member member) {
        double premium = 100.0; // Base premium value
        int age = calculateAge(member);
        String gender = member.getGender();


        // Apply rules to calculate premium
        if (age > 0) {
            int ageIncreaseFactor = (int) Math.ceil(age / 5.0);
            double agePremiumIncrease = ageIncreaseFactor * 0.1;
            premium *= (1.0 + agePremiumIncrease);
        }

        if (gender.equalsIgnoreCase("female")) {
            premium *= 0.95;
        }
        return premium;
    }
    @Autowired
    private MemberPremiumRepo memberPremiumRepo;

    public MemberPremium savePremium(MemberPremium member)
    {
        return memberPremiumRepo.save(member);
    }
}

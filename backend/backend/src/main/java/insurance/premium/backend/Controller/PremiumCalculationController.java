package insurance.premium.backend.Controller;

import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Entity.MemberPremium;
import insurance.premium.backend.Service.MemberPremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class PremiumCalculationController {
    @Autowired
     private MemberPremiumService memberPremiumService;


    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping("api/v1/member")

    @PostMapping("/set-premium")
    public ResponseEntity<?> setMemberPremium(Member member){
        double calculatedPremium = memberPremiumService.calculatePremium(member);
        MemberPremium memberPremium = new MemberPremium(member.getMemberId(),calculatedPremium);
        memberPremiumService.savePremium(memberPremium);
        return new ResponseEntity<> (" Premium calculation successful", HttpStatus.CREATED);
    }
}

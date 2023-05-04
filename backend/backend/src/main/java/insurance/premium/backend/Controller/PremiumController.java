package insurance.premium.backend.Controller;


import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Entity.Plan;
import insurance.premium.backend.Entity.Policy;
import insurance.premium.backend.Repo.MemberRepo;
import insurance.premium.backend.Service.MemberService;
import insurance.premium.backend.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/member")
public class PremiumController {


    @Autowired
    private PolicyService policyService;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private MemberService memberService;


    // get the different plans that are available for a person based on the calculated premium
    @GetMapping("/premiums/{email}")
    public List<Plan> getPlanDetails(@PathVariable String email) {
        try {
            Member member = memberService.getMemberByEmail(email);
            List<Plan> plans = new ArrayList<>();
            Policy policy = policyService.calculatePremium(member);
            double premium = policy.getPremium();
            plans = policyService.calculatePlans(premium);
            return plans;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Plan>();
        }
    }



    // get the premium amount for a user
    @GetMapping("/premium/{email}")
    public ResponseEntity<?> getPremium(@PathVariable String email) {
        try {
            Member member = memberService.getMemberByEmail(email);
            Policy policy = policyService.calculatePremium(member);
            return ResponseEntity.ok(policy);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to calculate premium for member with email: " + email);
        }
    }





}
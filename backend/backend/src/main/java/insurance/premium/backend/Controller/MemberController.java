package insurance.premium.backend.Controller;

import insurance.premium.backend.Entity.LoginRequest;
import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Entity.Plan;
import insurance.premium.backend.Entity.Policy;
import insurance.premium.backend.Repo.MemberRepo;
import insurance.premium.backend.Service.MemberService;
import insurance.premium.backend.Service.PolicyService;
import insurance.premium.backend.security.JwtUtil;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/member")
public class MemberController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private KieSession session;
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private PolicyService policyService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Member member) {
        try {
            Member registeredMember = memberService.registerMember(member);
            return new ResponseEntity<>(registeredMember, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {

            if (ex.getMessage().contains("email")) {
                return new ResponseEntity<>("Email address already exists", HttpStatus.BAD_REQUEST);
            } else if (ex.getMessage().contains("gov_id")) {
                return new ResponseEntity<>("Government ID already exists", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("An error occurred while registering the member", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }


    @GetMapping("/user/{email}")
    public Member getUserByEmail(@PathVariable String email) {

        Member member = memberRepo.findByEmail(email);
        return member;
    }









    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();


        if (isValidUser(email, password)) {

            String token = JwtUtil.generateToken(email);
            return ResponseEntity.ok(token);

        } else {
            if (!isValidEmail(email)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        }
    }

    private boolean isValidEmail(String email) {


        Member member = memberRepo.findByEmail(email);
        if (member != null) {
            return true;
        }

        return false;
}


    private boolean isValidUser(String email, String password) {
        boolean isEmailValid = false;
        boolean isPasswordValid = false;
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        Member member = memberRepo.findByEmail(email);
        if(member != null)
        {
            if(bcrypt.matches(password,member.getPassword()))
            {
                isEmailValid = true;
                isPasswordValid = true;
            } else {
                isEmailValid = true;
            }
        }
        return (isEmailValid && isPasswordValid);
    }
    
    
    @GetMapping("/premiums/{email}")
    public List<Plan> getPlanDetails(@PathVariable String email)
    {
        Member member = memberRepo.findByEmail(email);
        List<Plan> plans = new ArrayList<>();
        Policy policy = policyService.calculatePremium(member);
        double premium = policy.getPremium();
        plans = policyService.calculatePlans(premium);

        return plans;


    }


    @GetMapping("/premium/{email}")
    public Policy getPremium(@PathVariable String email) {
        Member member = memberRepo.findByEmail(email);
        Policy policy=policyService.calculatePremium(member);
        return policy;
    }



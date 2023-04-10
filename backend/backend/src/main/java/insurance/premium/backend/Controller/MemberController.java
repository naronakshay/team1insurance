package insurance.premium.backend.Controller;

import insurance.premium.backend.Entity.LoginRequest;
import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Service.MemberService;
import insurance.premium.backend.Service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Member member) {
        try {

            BCryptPasswordEncoder bCryptPassword = new BCryptPasswordEncoder();
            String encrypPassword = bCryptPassword.encode(member.getPassword());
            member.setPassword(encrypPassword);

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {


        try {




            Member member = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(member);
        } catch (MemberServiceImpl.AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }
    }






}

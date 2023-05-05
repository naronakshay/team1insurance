package insurance.premium.backend.Controller;
import insurance.premium.backend.Entity.LoginRequest;
import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Exceptions.MemberNotFoundException;
import insurance.premium.backend.Exceptions.MemberRegistrationException;
import insurance.premium.backend.Service.MemberService;
import insurance.premium.backend.security.JwtUtil;

import io.jsonwebtoken.JwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/member")
public class MemberController {


    @Autowired
    private MemberService memberService;
    @Autowired
    private JwtUtil jwtUtil;

    Logger memberLogger = LoggerFactory.getLogger(MemberController.class);



    //register the new user into the database
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Member member) {
        try {
            Member registeredMember = memberService.registerMember(member);
            return new ResponseEntity<>(registeredMember, HttpStatus.CREATED);
        } catch (MemberRegistrationException ex) {
            memberLogger.error("Registration unsuccessful",ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    //return the details of the user by email
    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String authHeader) {
        try {

            String token = authHeader.substring(7);
            String decodedEmail = jwtUtil.getEmailFromToken(token);
            if (!decodedEmail.equals(email)) {
                return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
            }
            Member member = memberService.getMemberByEmail(email);
            System.out.println(member);
            return new ResponseEntity<>(member, HttpStatus.OK);

        }
        catch (JwtException ex) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
            memberLogger.error("Invalid Token");
        }catch (MemberNotFoundException ex) {

        } catch (MemberNotFoundException ex) {
            memberLogger.error("Member not found : "+email,ex);

            
        } catch (Exception ex) {
            memberLogger.error("Member not found : "+email,ex);
            return new ResponseEntity<>("An error occurred while fetching member details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // checks the authentication of the user and returns jwt token if authenticated
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            if (memberService.login(loginRequest)) {
                String token = JwtUtil.generateToken(loginRequest.getEmail());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (MemberNotFoundException e) {
            memberLogger.error("Member not found : "+loginRequest.getEmail(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password");
        } catch (Exception e) {
            memberLogger.error("Member not found : "+loginRequest.getEmail(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
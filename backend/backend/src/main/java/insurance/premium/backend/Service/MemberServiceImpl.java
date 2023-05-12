package insurance.premium.backend.Service;
import insurance.premium.backend.Entity.LoginRequest;
import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Exceptions.MemberNotFoundException;
import insurance.premium.backend.Exceptions.MemberRegistrationException;
import insurance.premium.backend.Repo.MemberRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepo memberRepo;

    Logger memberServicelogger = LoggerFactory.getLogger(MemberServiceImpl.class);

    // save the details of the member into the member table
    public Member registerMember(Member member) throws MemberRegistrationException {
        try {
            BCryptPasswordEncoder bCryptPassword = new BCryptPasswordEncoder();
            String encrypt = bCryptPassword.encode(member.getPassword());
            member.setPassword(encrypt);
            member.setFirstName(member.getFirstName().toLowerCase());
            member.setLastName(member.getLastName().toLowerCase());
            member.setCity(member.getCity().toLowerCase());
            member.setAddress(member.getAddress().toLowerCase());
            member.setState(member.getState().toLowerCase());
            member.setIllnessDetails(member.getIllnessDetails().toLowerCase());
            member.setGender(member.getGender().toLowerCase());
            return memberRepo.save(member);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("email")) {
                memberServicelogger.error("Email address already exists",ex);
                throw new MemberRegistrationException("Email address already exists", ex);
            } else if (ex.getMessage().contains("gov_id")) {
                memberServicelogger.error("Government ID already exists",ex);
                throw new MemberRegistrationException("Government ID already exists", ex);
            } else {
                memberServicelogger.error("An error occurred while registering the member",ex);
                throw new MemberRegistrationException("An error occurred while registering the member", ex);
            }
        }
    }


    // get the details of the member from the table by email
    public Member getMemberByEmail (String email){
        Member member = memberRepo.findByEmail(email);
        if (member == null) {
            memberServicelogger.error("Member not found for email:"+email);
            throw new MemberNotFoundException("Member not found for email: " + email);
        }
        return member;
    }




    // checking the authentication of user
    public Boolean login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Member member = memberRepo.findByEmail(email);
        if (member == null) {
            memberServicelogger.error("Member with email " + email + " not found");
            throw new MemberNotFoundException("Member with email " + email + " not found");
        }

        if (member != null && bcrypt.matches(password, member.getPassword())) {
            return true;
        } else {
            return false;
        }
    }











}
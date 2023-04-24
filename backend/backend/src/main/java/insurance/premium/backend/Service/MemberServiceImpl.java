package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepo memberRepo;

    public Member registerMember(Member member) {
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
    }










}
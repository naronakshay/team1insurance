package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepo memberRepo;

    public class AuthenticationException extends RuntimeException {
        public AuthenticationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public Member registerMember(Member member) {
        return memberRepo.save(member);
    }

    public Member login(String email, String password) {
        BCryptPasswordEncoder bCryptPassword = new BCryptPasswordEncoder();

        Member member = memberRepo.findByEmail(email);

        Member m = null;
        if (member != null){
            if(bCryptPassword.matches(member.getPassword(),password))
            {
                m=member;
            }
        } else {
            throw new AuthenticationException("Invalid login credentials.", null);
            
        }
        return m;
    }
}
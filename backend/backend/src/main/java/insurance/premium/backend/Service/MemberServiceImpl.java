package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.Member;
import insurance.premium.backend.Repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
        Member member = memberRepo.findByEmail(email);

        if (member != null && member.getPassword().equals(password)) {
            return member;
        } else {
            throw new AuthenticationException("Invalid login credentials.", null);
        }
    }
}
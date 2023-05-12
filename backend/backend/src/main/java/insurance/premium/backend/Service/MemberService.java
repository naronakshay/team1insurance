package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.LoginRequest;
import insurance.premium.backend.Entity.Member;

public interface MemberService {

    Member registerMember(Member member);
    Member getMemberByEmail(String email);

    Boolean login(LoginRequest loginRequest);
}


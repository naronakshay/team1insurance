package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.Member;

public interface MemberService {

    Member registerMember(Member member);
    Member login(String email, String password);
}

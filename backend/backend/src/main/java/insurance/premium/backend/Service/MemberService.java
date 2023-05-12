package insurance.premium.backend.service;

import insurance.premium.backend.entity.LoginRequest;
import insurance.premium.backend.entity.Member;

/**
 * Interface for member related services.
 */
public interface MemberService {

    /**
     * Registers a new member.
     * @param member The member to be registered.
     * @return The registered member object.
     */
    Member registerMember(Member member);

    /**
     * Retrieves a member by email address.
     * @param email The email address of the member.
     * @return The member object if found, else null.
     */
    Member getMemberByEmail(String email);

    /**
     * Authenticates a member based on their login credentials.
     * @param loginRequest The login request object containing email and password.
     * @return True if authentication is successful, else false.
     */
    Boolean login(LoginRequest loginRequest);
}
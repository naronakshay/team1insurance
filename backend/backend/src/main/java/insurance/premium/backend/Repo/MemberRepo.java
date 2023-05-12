package insurance.premium.backend.Repo;

import insurance.premium.backend.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface MemberRepo extends JpaRepository<Member,String> {

     /**
     * Find a member by email.
     *
     * @param email the email of the member to find
     * @return the member with the given email, or null if not found
     */

    Member findByEmail(String email);




}

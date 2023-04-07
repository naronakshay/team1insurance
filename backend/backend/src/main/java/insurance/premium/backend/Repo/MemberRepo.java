package insurance.premium.backend.Repo;

import insurance.premium.backend.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface MemberRepo extends JpaRepository<Member,String> {

    Member findByEmail(String email);

}

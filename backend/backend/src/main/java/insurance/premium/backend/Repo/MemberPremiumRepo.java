package insurance.premium.backend.Repo;

import insurance.premium.backend.Entity.MemberPremium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPremiumRepo extends JpaRepository<MemberPremium,Integer> {
}

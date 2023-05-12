package insurance.premium.backend.Repo;

import insurance.premium.backend.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<State,Integer> {
}
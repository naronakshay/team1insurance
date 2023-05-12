package insurance.premium.backend.Repo;

import insurance.premium.backend.Entity.Plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepo extends JpaRepository<Plan, Integer> {
    List<Plan> findAll();
    /**
   * Finds a plan by its type.
   *
   * @param planType the type of the plan to find
   * @return the plan with the specified type, or null if not found
   */

    Plan findByPlanType(String planType);





}


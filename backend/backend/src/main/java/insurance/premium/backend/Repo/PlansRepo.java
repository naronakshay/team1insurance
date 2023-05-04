package insurance.premium.backend.Repo;

import insurance.premium.backend.Entity.Plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepo extends JpaRepository<Plan, Integer> {
    List<Plan> findAll();

    Plan findByPlanType(String planType);





}


package insurance.premium.backend.Repo;

import insurance.premium.backend.Entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, Integer> {
     // Get all diseases
    List<Disease> findAll();
    
    // Find disease by name
    Disease findByDiseaseName(String diseaseName);



}


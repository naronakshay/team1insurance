package insurance.premium.backend.Repo;

import insurance.premium.backend.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City,Integer> {
    List<City> findByStateId(int stateId);
    City findByCityName(String city_name);
}


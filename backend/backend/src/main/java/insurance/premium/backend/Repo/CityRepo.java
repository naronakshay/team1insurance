package insurance.premium.backend.repo;

import insurance.premium.backend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for City entity.
 */
public interface CityRepo extends JpaRepository<City, Integer> {

    /**
     * Retrieves a list of cities by state ID.
     *
     * @param stateId the ID of the state
     * @return a list of cities in the given state
     */
    List<City> findByStateId(int stateId);

    /**
     * Retrieves a city by city name.
     *
     * @param cityName the name of the city
     * @return the city with the given name
     */
    City findByCityName(String cityName);

}

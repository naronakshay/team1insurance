package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.City;
import insurance.premium.backend.Entity.State;
import insurance.premium.backend.Repo.CityRepo;
import insurance.premium.backend.Repo.StateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to handle operations related to State and City.
 */
@Service
public class StateCityService {

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CityRepo cityRepo;

    /**
     * Retrieves all States from the database.
     *
     * @return List of all States
     */
    public List<State> getStates() {
        return stateRepo.findAll();
    }

    /**
     * Retrieves all Cities for a given State ID from the database.
     *
     * @param stateId ID of the State for which Cities need to be retrieved
     * @return List of all Cities for the given State ID
     */
    public List<City> getCitiesByStateId(int stateId) {
        return cityRepo.findByStateId(stateId);
    }
}

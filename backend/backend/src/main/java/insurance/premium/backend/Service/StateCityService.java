package insurance.premium.backend.Service;

import insurance.premium.backend.Entity.City;
import insurance.premium.backend.Entity.State;
import insurance.premium.backend.Repo.CityRepo;
import insurance.premium.backend.Repo.StateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StateCityService {

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CityRepo cityRepo;

     public List<State> getStates(){
         return  stateRepo.findAll();
     }

    public List<City> getCitiesByStateId( int stateId){
        return cityRepo.findByStateId(stateId);
    }



}

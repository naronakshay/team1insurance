package insurance.premium.backend.Controller;

import insurance.premium.backend.Entity.City;
import insurance.premium.backend.Entity.State;
import insurance.premium.backend.Repo.CityRepo;
import insurance.premium.backend.Repo.StateRepo;
import insurance.premium.backend.Service.StateCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("api/v1/member")
public class StateCityController {


    @Autowired
    StateCityService stateCityService;



    // get all rows of state table
    @GetMapping("/states")
    public List<State> getStates(){
        List<State> States= stateCityService.getStates();
        return States;
    }


    //get all rows of city table with specified state_id
    @GetMapping("/cities/{stateId}")
    public List<City> getCitiesByStateId(@PathVariable int stateId)
    {
        List<City> Cities=stateCityService.getCitiesByStateId(stateId);
        return  Cities;
    }

}

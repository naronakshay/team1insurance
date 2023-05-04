package insurance.premium.backend.Controller;

import insurance.premium.backend.Entity.Disease;
import insurance.premium.backend.Repo.DiseaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/member")


public class DiseaseController {
    @Autowired
    private DiseaseRepo diseaseRepo;

    @GetMapping("/disease")
    public List<Disease> getAllDiseases(){

        return diseaseRepo.findAll();

    }




}

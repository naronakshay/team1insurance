package insurance.premium.backend.Entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Policy {
    String gender;
    Boolean istobaccoUser;
    int age;
    double premium = 0.0;
    Boolean istier1City;
    double illnesspremium;
}

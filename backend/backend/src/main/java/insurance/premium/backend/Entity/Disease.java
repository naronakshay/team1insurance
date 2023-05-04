package insurance.premium.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

=======

@Entity

@Table(name = "disease")
public class Disease {

    @Id
    @Column(name="disease_id")
    int disease_id;

    @Column(name="disease_name")
    String diseaseName;

    @Column(name="additional_premium")
    double additional_premium;

    public int getDisease_id() {
        return disease_id;
    }

    public void setDisease_id(int disease_id) {
        this.disease_id = disease_id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public double getAdditional_premium() {
        return additional_premium;
    }

    public void setAdditional_premium(double additional_premium) {
        this.additional_premium = additional_premium;
    }

    public Disease(int disease_id, String diseaseName, double additional_premium) {
        this.disease_id = disease_id;
        this.diseaseName = diseaseName;
        this.additional_premium = additional_premium;
    }

    public Disease() {
    }

}


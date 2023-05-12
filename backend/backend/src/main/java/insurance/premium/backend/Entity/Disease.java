package insurance.premium.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "disease")
public class Disease {

    @Id
    @Column(name="disease_id")
    int disease_id;

    @Column(name="disease_name")
    String diseaseName;

    @Column(name="additional_premium")
    double additional_premium;
}


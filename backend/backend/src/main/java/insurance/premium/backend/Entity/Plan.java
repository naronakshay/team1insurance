package insurance.premium.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @Column(name = "plan_id")
    int plan_id;
    @Column(name = "plan_type")
    String planType;
    @Column(name = "coverage")
    int coverage;

    @Column(name = "plan_details")
    String plan_details;

    @Column(name = "additional_premium")
    Double additional_premium;

    @Column(name = "cashless_hospitals")
    int cashless_hospitals;


    @Transient
    double finalPremium;
    @Transient
    int monthlyPremium;







}



package insurance.premium.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="city")
@Getter
@Setter
public class City
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private int id;
    @Column(name="cityName")
    private String cityName;
    @Column(name = "tier1_city")
    private boolean tier_1;
    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;
}


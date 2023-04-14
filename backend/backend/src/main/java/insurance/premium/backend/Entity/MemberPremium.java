package insurance.premium.backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="premium_info_table")

public class MemberPremium {
    public MemberPremium(int memberId, double calculatedPremium) {
        this.memberId = memberId;
        this.calculatedPremium = calculatedPremium;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;
    @Column(name = "calculated_premium")
    private double calculatedPremium;

    public double getCalculatedPremium() {
        return calculatedPremium;
    }

    public void setCalculatedPremium(double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}

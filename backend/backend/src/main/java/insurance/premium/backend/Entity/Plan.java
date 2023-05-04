package insurance.premium.backend.Entity;

import jakarta.persistence.*;



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

    @Column(name ="cashless_hospitals")
    int cashless_hospitals;

    public int getPlan_id() {
        return plan_id;
    }

    public Double getAdditional_premium() {
        return additional_premium;
    }

    public void setAdditional_premium(Double additional_premium) {
        this.additional_premium = additional_premium;
    }

    @Transient
    double finalPremium;
    @Transient
    int monthlyPremium;

    public int getMonthlyPremium() {
        return monthlyPremium;
    }

    public void setMonthlyPremium(int monthlyPremium) {
        this.monthlyPremium = monthlyPremium;
    }

    public Plan(int plan_id, String planType, int coverage, String plan_details, Double additional_premium, int cashless_hospitals) {
        this.plan_id = plan_id;
        this.planType = planType;
        this.coverage = coverage;
        this.plan_details = plan_details;
        this.additional_premium = additional_premium;
        this.cashless_hospitals = cashless_hospitals;
    }

    public String getPlan_details() {
        return plan_details;
    }

    public void setPlan_details(String plan_details) {
        this.plan_details = plan_details;
    }

    public int getCashless_hospitals() {
        return cashless_hospitals;
    }

    public void setCashless_hospitals(int cashless_hospitals) {
        this.cashless_hospitals = cashless_hospitals;
    }

    public Plan() {
    }


    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }


    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }

    public double getFinalPremium() {
        return finalPremium;
    }

    public void setFinalPremium(double finalPremium) {
        this.finalPremium = finalPremium;
    }
}



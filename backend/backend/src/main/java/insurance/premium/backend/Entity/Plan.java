package insurance.premium.backend.Entity;


public class Plan {

    int plan_id;
    String plan_type;
    int coverage;

    String plan_details;
    double finalPremium;
    int monthlyPremium;

    public int getMonthlyPremium() {
        return monthlyPremium;
    }

    public void setMonthlyPremium(int monthlyPremium) {
        this.monthlyPremium = monthlyPremium;
    }

    int cashless_hospitals;

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




    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
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

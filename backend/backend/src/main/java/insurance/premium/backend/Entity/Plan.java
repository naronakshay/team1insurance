package insurance.premium.backend.Entity;

public class Plan {

    int plan_id;
    String plan_type;
    int coverage;

    double finalPremium;


    public int getPlan_id() {
        return plan_id;
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

package insurance.premium.backend.Entity;

public class Policy {
    String gender;
    Boolean istobaccoUser;
    int age;

    double premium;

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getIstobaccoUser() {
        return istobaccoUser;
    }

    public void setIstobaccoUser(Boolean istobaccoUser) {
        this.istobaccoUser = istobaccoUser;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

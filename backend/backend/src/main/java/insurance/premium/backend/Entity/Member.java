package insurance.premium.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "member_table")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gov_id")
    private String govId;


    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private Date dob;

    @Column(name="state")
    private String state;


    @Column(name = "is_tobacco_user")
    private Boolean isTobaccoUser= false;;

    @Column(name = "city")
    private String city;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "address")
    private String address;

    @Column(name = "illness_Details")
    private String illnessDetails;

    public Member() {
    }

    public Member(int memberId, String email, String firstName, String lastName, String govId, String phoneNumber, String gender, Date dob, String state, Boolean isTobaccoUser, String city, String pinCode, String address, String illnessDetails, String password) {
        this.memberId = memberId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.govId = govId;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dob = dob;
        this.state = state;
        this.isTobaccoUser = isTobaccoUser;
        this.city = city;
        this.pinCode = pinCode;
        this.address = address;
        this.illnessDetails = illnessDetails;
        this.password = password;
    }

    @Column(name = "password")
    private String password;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGovId() {
        return govId;
    }

    public void setGovId(String govId) {
        this.govId = govId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getTobaccoUser() {
        return isTobaccoUser;
    }

    public void setTobaccoUser(Boolean tobaccoUser) {
        isTobaccoUser = tobaccoUser;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIllnessDetails() {
        return illnessDetails;
    }

    public void setIllnessDetails(String illnessDetails) {
        this.illnessDetails = illnessDetails;
    }

    public String getPassword() {
        return password;
    }

    public Date getDob() {
        return dob;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
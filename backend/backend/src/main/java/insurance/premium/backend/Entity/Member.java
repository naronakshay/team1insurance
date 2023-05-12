package insurance.premium.backend.entity;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a member of an insurance plan.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_table")
public class Member {

  /**
   * The unique ID for this member.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private int memberId;

  /**
   * The email address of this member.
   */
  @Column(name = "email")
  private String email;

  /**
   * The first name of this member.
   */
  @Column(name = "first_name")
  private String firstName;

  /**
   * The last name of this member.
   */
  @Column(name = "last_name")
  private String lastName;

  /**
   * The government ID of this member.
   */
  @Column(name = "gov_id")
  private String govId;

  /**
   * The phone number of this member.
   */
  @Column(name = "phone_number")
  private String phoneNumber;

  /**
   * The gender of this member.
   */
  @Column(name = "gender")
  private String gender;

  /**
   * The date of birth of this member.
   */
  @Column(name = "dob")
  private Date dob;

  /**
   * The state where this member resides.
   */
  @Column(name = "state")
  private String state;

  /**
   * Whether this member uses tobacco.
   */
  @Column(name = "is_tobacco_user")
  private Boolean isTobaccoUser = false;

  /**
   * The city where this member resides.
   */
  @Column(name = "city")
  private String city;

  /**
   * The postal code of this member's address.
   */
  @Column(name = "pin_code")
  private String pinCode;

  /**
   * The address of this member.
   */
  @Column(name = "address")
  private String address;

  /**
   * Details of any illnesses this member has.
   */
  @Column(name = "illness_details")

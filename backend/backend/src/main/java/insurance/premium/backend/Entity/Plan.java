

package insurance.premium.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Represents an insurance plan.
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "plans")
public class Plan {

  /**
   * The unique ID for this plan.
   */
  @Id
  @Column(name = "plan_id")
  private int planId;

  /**
   * The type of this plan.
   */
  @Column(name = "plan_type")
  private String planType;

  /**
   * The coverage amount for this plan.
   */
  @Column(name = "coverage")
  private int coverage;

  /**
   * Details of this plan.
   */
  @Column(name = "plan_details")
  private String planDetails;

  /**
   * Additional premium amount for this plan.
   */
  @Column(name = "additional_premium")
  private Double additionalPremium;

  /**
   * The number of cashless hospitals covered by this plan.
   */
  @Column(name = "cashless_hospitals")
  private int cashlessHospitals;

  /**
   * The final premium amount for this plan.
   * This field should not be persisted in the database.
   */
  @Transient
  private double finalPremium;

  /**
   * The monthly premium amount for this plan.
   * This field should not be persisted in the database.
   */
  @Transient
  private int monthlyPremium;

}

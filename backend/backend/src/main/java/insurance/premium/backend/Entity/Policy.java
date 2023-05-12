package insurance.premium.backend.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a policy.
 */
@Getter
@Setter
public class Policy {

  /**
   * The gender of the policy holder.
   */
  private String gender;

  /**
   * Indicates whether the policy holder is a tobacco user.
   */
  private Boolean isTobaccoUser;

  /**
   * The age of the policy holder.
   */
  private int age;

  /**
   * The base premium for the policy.
   */
  private double premium = 0.0;

  /**
   * Indicates whether the policy holder is from a tier-1 city.
   */
  private Boolean isTier1City;

  /**
   * The additional premium amount for any pre-existing illnesses.
   */
  private double illnessPremium;

}


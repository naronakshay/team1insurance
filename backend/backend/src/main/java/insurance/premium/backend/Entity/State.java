package insurance.premium.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a state in which insurance policy is issued.
 */
@Entity
@Table(name = "state")
@Getter
@Setter
public class State {

  /**
   * The unique ID of the state.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "state_id")
  private int id;

  /**
   * The name of the state.
   */
  @Column(name = "state_name")


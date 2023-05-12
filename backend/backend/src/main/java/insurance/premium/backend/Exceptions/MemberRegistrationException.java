package insurance.premium.backend.exceptions;

/**
 * Exception thrown when there is an error with member registration.
 */
public class MemberRegistrationException extends RuntimeException {

  public MemberRegistrationException(String message, Throwable cause) {
    super(message, cause);
  }

}

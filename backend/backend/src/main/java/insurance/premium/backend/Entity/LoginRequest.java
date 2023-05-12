package insurance.premium.backend.entity;

/**
 * A data transfer object (DTO) representing a login request in an insurance premium backend system.
 */
public class LoginRequest {

    private String email; // the email of the user attempting to log in
    private String password; // the password of the user attempting to log in

    /**
     * Creates a new LoginRequest object with default values for the email and password.
     */
    public LoginRequest() {
    }

    /**
     * Creates a new LoginRequest object with the given email and password.
     * 
     * @param email the email of the user attempting to log in
     * @param password the password of the user attempting to log in
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the email of the user attempting to log in.
     * 
     * @return the email of the user attempting to log in
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user attempting to log in.
     * 
     * @param email the email of the user attempting to log in
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the user attempting to log in.
     * 
     * @return the password of the user attempting to log in
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user attempting to log in.
     * 
     * @param password the password of the user attempting to log in
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

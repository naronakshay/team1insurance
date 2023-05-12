package insurance.premium.backend.security;

import io.jsonwebtoken.*;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET_KEY;
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    // Initialize the secret key with a random value
    static {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[64];
        secureRandom.nextBytes(key);
        SECRET_KEY = Base64.getEncoder().encodeToString(key);
        System.out.println(SECRET_KEY);
    }

    /**
     * Generates a JWT token with the provided email as the subject and an expiration time of 24 hours.
     *
     * @param email The email to use as the token subject.
     * @return The generated JWT token.
     */
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Retrieves the email subject from the provided JWT token.
     *
     * @param token The JWT token from which to retrieve the email subject.
     * @return The email subject retrieved from the JWT token.
     */
    public String getEmailFromToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);

        return claims.getBody().getSubject();
    }

    /**
     * Validates the provided JWT token by verifying the signature and checking the expiration time.
     *
     * @param token The JWT token to validate.
     * @return True if the token is valid, false otherwise.
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}

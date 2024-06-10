package pl.foodoutlet.foodoutlet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import pl.foodoutlet.foodoutlet.model.User;
import pl.foodoutlet.foodoutlet.repository.UserRepo;

/**
 * The AuthService class provides authentication-related services,
 * including user sign-up and sign-in operations.
 *
 * @author Raymond
 * @version 1.0
 */
@Service
public class AuthService {

    /**
     * The repository for accessing User entities.
     */
    @Autowired
    public UserRepo userRepo;

    /**
     * The Argon2PasswordEncoder used for hashing passwords.
     * It is configured with specific parameters for hashing.
     */
    private Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2);

    /**
     * Signs up a new user by encoding the password and saving the user to the
     * repository.
     *
     * @param user The User object representing the user to be signed up.
     * @return The User object representing the signed-up user.
     */
    public User signUp(User user) {
        String unHashedPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(unHashedPassword));
        return userRepo.save(user);
    }

    /**
     * Signs in a user by verifying the provided credentials against the stored
     * data.
     *
     * @param user The User object representing the user attempting to sign in.
     * @return The User object representing the signed-in user if successful, or
     *         null otherwise.
     */
    public User signIn(User user) {
        try {
            User signingUser = userRepo.findByUsername(user.getUsername());
            boolean passwordVerify = passwordEncoder.matches(user.getPassword(), signingUser.getPassword());

            if (passwordVerify) {
                return signingUser;
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "user not found");
            }

        } catch (Exception e) {
            return null;
        }
    }

}

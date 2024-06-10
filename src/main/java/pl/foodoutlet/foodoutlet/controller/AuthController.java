package pl.foodoutlet.foodoutlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.foodoutlet.foodoutlet.model.User;
import pl.foodoutlet.foodoutlet.service.AuthService;

/**
 * The AuthController class handles authentication-related HTTP requests.
 * It provides endpoints for user sign-up and sign-in operations.
 *
 * @author Princesse
 * @version 1.0
 * 
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Handles the HTTP POST request for user sign-up.
     *
     * @author Princesse
     * @param user The User object containing sign-up information.
     * @return The User object representing the signed-up user.
     */
    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        return authService.signUp(user);
    }

    /**
     * Handles the HTTP POST request for user sign-in.
     * 
     * @author Princesse
     * @param user The User object containing sign-in information.
     * @return The User object representing the signed-in user.
     * 
     */
    @PostMapping("/signin")
    public User signIn(@RequestBody User user) {
        return authService.signIn(user);
    }
}

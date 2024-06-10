package pl.foodoutlet.foodoutlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.foodoutlet.foodoutlet.model.User;

/**
 * The UserRepo interface extends JpaRepository for User entities.
 * It provides CRUD (Create, Read, Update, Delete) operations for the User
 * entity.
 *
 * @author Miguel
 * @version 1.0
 * 
 */
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Finds a user by the specified username.
     *
     * @param username The username of the user to find.
     * @return The User object with the specified username, or null if not found.
     * 
     */
    public User findByUsername(String username);

}

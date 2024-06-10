package pl.foodoutlet.foodoutlet.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

/**
 * The User class represents a user entity in the application.
 * It is annotated with JPA annotations to map it to the database table "users".
 *
 * @author Raymond
 * @version 1.0
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     */
    @NotEmpty
    @Column(name = "username")
    private String username;

    /**
     * The password of the user.
     */
    @NotEmpty
    @Column(name = "password")
    private String password;

    /**
     * The list of ratings associated with the user.
     * It is annotated with OneToMany relationship, indicating a one-to-many
     * association
     * with the "ratings" table. CascadeType.ALL ensures that operations on User are
     * cascaded
     * to the associated ratings.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    /**
     * Default constructor for the User class.
     */
    public User() {
    }

    /**
     * Parameterized constructor for the User class.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return The unique identifier of the user.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the list of ratings associated with the user.
     *
     * @return The list of ratings associated with the user.
     */
    public List<Rating> getRatings() {
        return this.ratings;
    }

    /**
     * Sets the list of ratings associated with the user.
     *
     * @param ratings The list of ratings to set.
     */
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

}

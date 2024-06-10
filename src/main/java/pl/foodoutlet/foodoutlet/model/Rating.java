package pl.foodoutlet.foodoutlet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Represents a user's rating for a food outlet.
 * Each instance of this class corresponds to a record in the "rating_review"
 * table.
 * The ratings are associated with a specific user and a food outlet.
 *
 * @author Miguel
 * @version 1.0
 * 
 */
@Entity
@Table(name = "rating_review")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_outlet_id")
    @JsonBackReference
    private FoodOutlet foodOutlet;

    /**
     * Default constructor for the Rating class.
     */
    public Rating() {
    }

    /**
     * Parameterized constructor for creating a Rating with a specific rating and
     * associated Food Outlet.
     *
     * @param rating     The numerical rating given by the user.
     * @param foodOutlet The Food Outlet for which the rating is provided.
     * 
     */
    public Rating(int rating, FoodOutlet foodOutlet) {
        this.rating = rating;
        this.foodOutlet = foodOutlet;
    }

    /**
     * Gets the unique identifier of the Rating.
     *
     * @return The unique identifier of the Rating.
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the numerical rating given by the user.
     *
     * @return The numerical rating.
     * 
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the numerical rating given by the user.
     *
     * @param rating The numerical rating to set.
     * 
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets the associated Food Outlet for this Rating.
     *
     * @return The associated Food Outlet.
     * 
     */
    public FoodOutlet getFoodOutlet() {
        return foodOutlet;
    }

    /**
     * Gets the unique identifier of the associated Food Outlet.
     *
     * @return The unique identifier of the associated Food Outlet.
     * 
     */
    public Long getFoodOutletId() {
        return this.foodOutlet.getId();
    }

    /**
     * Sets the associated Food Outlet for this Rating.
     *
     * @param foodOutlet The Food Outlet to set.
     * 
     */
    public void setFoodOutlet(FoodOutlet foodOutlet) {
        this.foodOutlet = foodOutlet;
    }

    /**
     * Sets the unique identifier of the Rating.
     *
     * @param id The unique identifier to set.
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }
}

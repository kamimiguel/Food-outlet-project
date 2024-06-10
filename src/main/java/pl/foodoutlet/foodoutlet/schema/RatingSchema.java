package pl.foodoutlet.foodoutlet.schema;

/**
 * Creates a schema for Request Body object used by Rating Controller
 * 
 * @author Princesse
 *
 */
public class RatingSchema {

    private int rating;
    private Long foodOutletId;

    // Constructors, getters, setters, etc.
    public RatingSchema() {
    }

    public RatingSchema(int rating, Long foodOutletId) {
        this.rating = rating;
        this.foodOutletId = foodOutletId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getFoodOutletId() {
        return foodOutletId;
    }

    public void setFoodOutletId(Long foodOutletId) {
        this.foodOutletId = foodOutletId;
    }
}

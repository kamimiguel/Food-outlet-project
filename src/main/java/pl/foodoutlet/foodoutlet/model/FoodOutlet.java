package pl.foodoutlet.foodoutlet.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

/**
 * Creates a schema for Request Body object used by Outlet Controller
 *
 * @author Miguel
 *
 */
@Entity
@Table(name = "food_outlet")
public class FoodOutlet {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @NotEmpty
        @Column(name = "name")
        private String name;

        @NotEmpty
        @Column(name = "address")
        private String address;

        @NotEmpty
        @Column(name = "cuisine_type")
        private String cuisineType;

        @Column(name = "opening_hours")
        private String openingHours;

        @Column(name = "image_url")
        private String imageUrl;

        // One-to-Many relationship: One FoodOutlet to Many Ratings
        @OneToMany(mappedBy = "foodOutlet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<Rating> ratings;;

        public FoodOutlet() {
        }

        public FoodOutlet(String name, String address, String cuisineType, String openingHours) {
                this.name = name;
                this.address = address;
                this.cuisineType = cuisineType;
                this.openingHours = openingHours;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getCuisineType() {
                return cuisineType;
        }

        public void setCuisineType(String cuisineType) {
                this.cuisineType = cuisineType;
        }

        public String getOpeningHours() {
                return openingHours;
        }

        public void setOpeningHours(String openingHours) {
                this.openingHours = openingHours;
        }

        public String getImageUrl() {
                return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
        }

        public List<Rating> getRatings() {
                return ratings;
        }

        public void setRatings(List<Rating> ratings) {
                this.ratings = ratings;
        }

}
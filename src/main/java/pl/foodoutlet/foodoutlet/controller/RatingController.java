package pl.foodoutlet.foodoutlet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.foodoutlet.foodoutlet.model.Rating;
import pl.foodoutlet.foodoutlet.schema.RatingSchema;
import pl.foodoutlet.foodoutlet.service.RatingService;

/**
 * Controller class for managing ratings in the Food Outlets Rating System.
 * Provides endpoints for CRUD operations on ratings.
 *
 * @author Miguel
 * @version 1.0
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/ratings")
public class RatingController {

  @Autowired
  private RatingService ratingService;

  /**
   * Creates a new rating for a food outlet.
   *
   * @param ratingRequest The request payload containing rating and foodOutletId.
   * @return The created Rating entity.
   * @author Miguel
   *
   */

  @PostMapping
  public Rating createRating(@RequestBody RatingSchema ratingRequest) {
    return ratingService.createRating(ratingRequest.getRating(), ratingRequest.getFoodOutletId());
  }

  /**
   * Calls Rating Service to Fetch all Ratings from the Database
   * 
   * @return A List of all Ratings
   * @author Miguel
   *
   */

  @GetMapping
  public List<Rating> getAllRatings() {
    return ratingService.getAllRatings();
  }

  /**
   * Retrieves a specific rating by its ID.
   *
   * @param id The ID of the rating to retrieve.
   * @return The Rating entity if found, or null if not found.
   * @author Miguel
   *
   */
  @GetMapping("/{id}")
  public Rating getRatingById(@PathVariable Long id) {
    return ratingService.getRatingById(id);
  }

  @PutMapping("/{id}")
  public Rating updateRating(@PathVariable Long id, @RequestBody RatingSchema ratingRequest) {
    return ratingService.updateRating(id, ratingRequest.getRating(), ratingRequest.getFoodOutletId());
  }

  @DeleteMapping("/{id}")
  public void deleteRating(@PathVariable Long id) {
    ratingService.deleteRating(id);
  }

}

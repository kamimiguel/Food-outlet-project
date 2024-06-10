package pl.foodoutlet.foodoutlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.foodoutlet.foodoutlet.model.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long> {

}

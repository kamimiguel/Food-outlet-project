package pl.foodoutlet.foodoutlet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.foodoutlet.foodoutlet.model.FoodOutlet;

public interface OutletRepo extends JpaRepository<FoodOutlet, Long> {

}

package pl.foodoutlet.foodoutlet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.foodoutlet.foodoutlet.model.FoodOutlet;
import pl.foodoutlet.foodoutlet.repository.OutletRepo;

/**
 * Service class for managing Food Outlets in the Food Outlet Rating System.
 * Provides business logic for CRUD operations on Food Outlets.
 *
 * @author Princesse
 * @version 1.0
 * 
 */
@Service
public class OutletService {

    @Autowired
    public OutletRepo outletRepo;

    /**
     * Creates a new Food Outlet.
     *
     * @author Princesse
     * @param outlet The Food Outlet object to be created.
     * @return The created Food Outlet.
     * 
     */
    public FoodOutlet createOutlet(FoodOutlet outlet) {
        return outletRepo.save(outlet);
    }

    /**
     * Retrieves a list of all Food Outlets.
     *
     * @author Princesse
     * @return List of Food Outlets.
     * 
     */
    public List<FoodOutlet> getAllOutlets() {
        return outletRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    /**
     * Retrieves a specific Food Outlet by its ID.
     *
     * @author Princesse
     * @param id The ID of the Food Outlet to retrieve.
     * @return The requested Food Outlet, or null if not found.
     * 
     */
    public FoodOutlet getOutletById(Long id) {
        return outletRepo.findById(id).orElse(null);
    }

    /**
     * Updates an existing Food Outlet.
     *
     * @author Princesse
     * @param id     The ID of the Food Outlet to update.
     * @param outlet The updated Food Outlet data.
     * @return The updated Food Outlet if successful, or null if the outlet doesn't
     *         exist.
     * 
     */
    public FoodOutlet updateOutlet(Long id, FoodOutlet outlet) {
        if (outletRepo.existsById(id)) {
            outlet.setId(id);
            return outletRepo.save(outlet);
        }
        return null;
    }

    /**
     * Deletes a Food Outlet by its ID.
     *
     * @author Princesse
     * @param id The ID of the Food Outlet to delete.
     * 
     */
    public void deleteOutlet(Long id) {
        outletRepo.deleteById(id);
    }

}

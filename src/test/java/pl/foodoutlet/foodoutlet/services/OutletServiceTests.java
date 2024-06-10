package pl.foodoutlet.foodoutlet.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import pl.foodoutlet.foodoutlet.model.FoodOutlet;
import pl.foodoutlet.foodoutlet.repository.OutletRepo;
import pl.foodoutlet.foodoutlet.service.OutletService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OutletServiceTests {

    @Mock
    private OutletRepo foodOutletRepository;

    @InjectMocks
    private OutletService foodOutletService;

    @Test
    public void testGetAllFoodOutlets() {
        FoodOutlet foodOutlet1 = new FoodOutlet("Restaurant", "123 Main St", "Italian", "9 AM - 9 PM");
        FoodOutlet foodOutlet2 = new FoodOutlet("Bistro", "456 Oak St", "French", "10 AM - 8 PM");

        Mockito.when(foodOutletRepository.findAll()).thenReturn(Arrays.asList(foodOutlet1, foodOutlet2));

        List<FoodOutlet> allFoodOutlets = foodOutletService.getAllOutlets();

        assertNotNull(allFoodOutlets);
        assertEquals(2, allFoodOutlets.size());
        assertEquals("Restaurant", allFoodOutlets.get(0).getName());
        assertEquals("Bistro", allFoodOutlets.get(1).getName());
    }

    @Test
    public void testGetFoodOutletById() {
        FoodOutlet foodOutlet = new FoodOutlet("Takeaway", "789 Pine St", "Asian", "11 AM - 10 PM");

        Mockito.when(foodOutletRepository.findById(1L)).thenReturn(Optional.of(foodOutlet));

        FoodOutlet retrievedFoodOutlet = foodOutletService.getOutletById(1L);

        assertNotNull(retrievedFoodOutlet);
        assertEquals("Takeaway", retrievedFoodOutlet.getName());
    }

    // Add more tests for other FoodOutletService methods
}

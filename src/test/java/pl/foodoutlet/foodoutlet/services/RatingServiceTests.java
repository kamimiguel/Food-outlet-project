package pl.foodoutlet.foodoutlet.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import pl.foodoutlet.foodoutlet.model.FoodOutlet;
import pl.foodoutlet.foodoutlet.model.Rating;
import pl.foodoutlet.foodoutlet.repository.OutletRepo;
import pl.foodoutlet.foodoutlet.repository.RatingRepo;
import pl.foodoutlet.foodoutlet.schema.RatingSchema;
import pl.foodoutlet.foodoutlet.service.RatingService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RatingServiceTests {

    @Mock
    private RatingRepo ratingRepository;

    @Mock
    private OutletRepo foodOutletRepository;

    @InjectMocks
    private RatingService ratingService;

    @Test
    public void testCreateRating() {
        RatingSchema ratingRequest = new RatingSchema(4, 1L);
        FoodOutlet foodOutlet = new FoodOutlet("Restaurant", "123 Main St", "Italian", "9 AM - 9 PM");

        Mockito.when(foodOutletRepository.findById(1L)).thenReturn(Optional.of(foodOutlet));
        Mockito.when(ratingRepository.save(Mockito.any())).thenReturn(new Rating(4, foodOutlet));

        Rating createdRating = ratingService.createRating(ratingRequest.getRating(), ratingRequest.getFoodOutletId());

        assertNotNull(createdRating);
        assertEquals(4, createdRating.getRating());
        assertEquals(foodOutlet, createdRating.getFoodOutlet());
    }

    @Test
    public void testGetAllRatings() {
        Rating rating1 = new Rating(4, new FoodOutlet("Bistro", "456 Oak St", "French", "10 AM - 8 PM"));
        Rating rating2 = new Rating(3, new FoodOutlet("Takeaway", "789 Pine St", "Asian", "11 AM - 10 PM"));

        Mockito.when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating1, rating2));

        List<Rating> allRatings = ratingService.getAllRatings();

        assertNotNull(allRatings);
        assertEquals(2, allRatings.size());
        assertEquals(4, allRatings.get(0).getRating());
        assertEquals(3, allRatings.get(1).getRating());
    }

}

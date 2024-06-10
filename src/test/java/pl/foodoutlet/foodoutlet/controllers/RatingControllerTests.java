package pl.foodoutlet.foodoutlet.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.foodoutlet.foodoutlet.controller.RatingController;
import pl.foodoutlet.foodoutlet.model.FoodOutlet;
import pl.foodoutlet.foodoutlet.model.Rating;
import pl.foodoutlet.foodoutlet.schema.RatingSchema;
import pl.foodoutlet.foodoutlet.service.RatingService;


/**
 * Tests for Rating Controller, making sure it works as inteneded
 *
 * @author Raymond
 *
 */
@WebMvcTest(RatingController.class)
public class RatingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateRating() throws Exception {
        RatingSchema ratingRequest = new RatingSchema(5, 1L);
        Rating createdRating = new Rating(5, new FoodOutlet("Restaurant", "123 Main St", "Italian", "9 AM - 9 PM"));

        Mockito.when(ratingService.createRating(Mockito.anyInt(), Mockito.anyLong())).thenReturn(createdRating);

        mockMvc.perform(post("/api/ratings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ratingRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(5));
    }

    @Test
    public void testGetAllRatings() throws Exception {
        Rating rating1 = new Rating(4, new FoodOutlet("Bistro", "456 Oak St", "French", "10 AM - 8 PM"));
        Rating rating2 = new Rating(3, new FoodOutlet("Takeaway", "789 Pine St", "Asian", "11 AM - 10 PM"));

        Mockito.when(ratingService.getAllRatings()).thenReturn(Arrays.asList(rating1, rating2));

        mockMvc.perform(get("/api/ratings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rating").value(4))
                .andExpect(jsonPath("$[1].rating").value(3));
    }

}

package pl.foodoutlet.foodoutlet.controllers;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.foodoutlet.foodoutlet.controller.OutletController;
import pl.foodoutlet.foodoutlet.model.FoodOutlet;
import pl.foodoutlet.foodoutlet.service.OutletService;

/**
 * Tests for Outlet Controller, making sure it works as inteneded
 *
 * @author Princesse
 *
 */
@WebMvcTest(OutletController.class)
public class OutletControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OutletService outletService;

    @Test
    public void testGetAllFoodOutlets() throws Exception {
        FoodOutlet foodOutlet1 = new FoodOutlet("Restaurant", "123 Main St", "Italian", "9 AM - 9 PM");
        FoodOutlet foodOutlet2 = new FoodOutlet("Bistro", "456 Oak St", "French", "10 AM - 8 PM");

        Mockito.when(outletService.getAllOutlets()).thenReturn(Arrays.asList(foodOutlet1, foodOutlet2));

        mockMvc.perform(get("/api/outlets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Restaurant"))
                .andExpect(jsonPath("$[1].name").value("Bistro"));
    }

    @Test
    public void testGetFoodOutletById() throws Exception {
        FoodOutlet foodOutlet = new FoodOutlet("Takeaway", "789 Pine St", "Asian", "11 AM - 10 PM");

        Mockito.when(outletService.getOutletById(1L)).thenReturn(foodOutlet);

        mockMvc.perform(get("/api/outlets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Takeaway"));
    }

    @Test
    public void testCreateFoodOutlet() throws Exception {
        FoodOutlet newFoodOutlet = new FoodOutlet("New Outlet", "789 Elm St", "Mexican", "12 PM - 9 PM");

        Mockito.when(outletService.createOutlet(Mockito.any(FoodOutlet.class))).thenReturn(newFoodOutlet);

        mockMvc.perform(post("/api/outlets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newFoodOutlet)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(newFoodOutlet)));
    }

    @Test
    public void testUpdateFoodOutlet() throws Exception {
        Long outletId = 1L;
        FoodOutlet updatedFoodOutlet = new FoodOutlet("Updated Outlet", "123 Maple St", "Indian", "10 AM - 8 PM");

        Mockito.when(outletService.updateOutlet(eq(outletId), any(FoodOutlet.class))).thenReturn(updatedFoodOutlet);

        mockMvc.perform(put("/api/outlets/{id}", outletId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedFoodOutlet)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(updatedFoodOutlet)));
    }

    @Test
    public void testDeleteFoodOutlet() throws Exception {
        Long outletId = 1L;

        Mockito.doNothing().when(outletService).deleteOutlet(eq(outletId));
        mockMvc.perform(delete("/api/outlets/{id}", outletId))
                .andExpect(status().is2xxSuccessful());
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package net.mercnet.filamently.controllers;

import net.mercnet.filamently.data.Filament;
import net.mercnet.filamently.data.FilamentRepository;
import net.mercnet.filamently.data.Inventory;
import net.mercnet.filamently.data.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryRepository inventoryRepository;

    @MockBean
    private FilamentRepository filamentRepository;

    @Test
    public void getAllInventory() throws Exception {
        Inventory inventory = new Inventory(null, LocalDate.now(), 1000.0, 0.0, 20.0);

        when(inventoryRepository.findAll()).thenReturn(Collections.singletonList(inventory));

        mockMvc.perform(get("/api/v0/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].startingWeight").value(1000.0));
    }

    @Test
    public void getInventoryById() throws Exception {
        Inventory inventory = new Inventory(null, LocalDate.now(), 1000.0, 0.0, 20.0);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        mockMvc.perform(get("/api/v0/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startingWeight").value(1000.0));
    }

    @Test
    public void getInventoryById_notFound() throws Exception {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v0/inventory/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createInventory() throws Exception {
        Filament filament = new Filament("Test Brand", null, "Red", "ff0000", 200, 60, 70, 0.5f);
        Inventory inventory = new Inventory(filament, LocalDate.now(), 1000.0, 0.0, 20.0);

        when(filamentRepository.existsById(1L)).thenReturn(true);
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        mockMvc.perform(post("/api/v0/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"filament\": { \"id\": 1 }, \"purchaseDate\": \"2024-01-01\", \"startingWeight\": 1000.0, \"usedWeight\": 0.0, \"pricePerKg\": 20.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startingWeight").value(1000.0));
    }

    @Test
    public void createInventory_filamentNotFound() throws Exception {
        when(filamentRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(post("/api/v0/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"filament\": { \"id\": 1 }, \"purchaseDate\": \"2024-01-01\", \"startingWeight\": 1000.0, \"usedWeight\": 0.0, \"pricePerKg\": 20.0 }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateInventory() throws Exception {
        Filament filament = new Filament("Test Brand", null, "Red", "ff0000", 200, 60, 70, 0.5f);
        Inventory inventory = new Inventory(filament, LocalDate.now(), 1000.0, 0.0, 20.0);
        Inventory updatedInventory = new Inventory(filament, LocalDate.now(), 1000.0, 100.0, 25.0);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));
        when(filamentRepository.existsById(1L)).thenReturn(true);
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(updatedInventory);

        mockMvc.perform(put("/api/v0/inventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"filament\": { \"id\": 1 }, \"purchaseDate\": \"2024-01-01\", \"startingWeight\": 1000.0, \"usedWeight\": 100.0, \"pricePerKg\": 25.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usedWeight").value(100.0));
    }

    @Test
    public void updateInventory_notFound() throws Exception {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v0/inventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"filament\": { \"id\": 1 }, \"purchaseDate\": \"2024-01-01\", \"startingWeight\": 1000.0, \"usedWeight\": 100.0, \"pricePerKg\": 25.0 }"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateInventory_filamentNotFound() throws Exception {
        Inventory inventory = new Inventory(null, LocalDate.now(), 1000.0, 0.0, 20.0);
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));
        when(filamentRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(put("/api/v0/inventory/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"filament\": { \"id\": 1 }, \"purchaseDate\": \"2024-01-01\", \"startingWeight\": 1000.0, \"usedWeight\": 100.0, \"pricePerKg\": 25.0 }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteInventory() throws Exception {
        when(inventoryRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/v0/inventory/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteInventory_notFound() throws Exception {
        when(inventoryRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/v0/inventory/1"))
                .andExpect(status().isNotFound());

        verify(inventoryRepository, never()).deleteById(1L);
    }
}

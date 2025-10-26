package net.mercnet.filamently.controllers;

import net.mercnet.filamently.data.Filament;
import net.mercnet.filamently.data.FilamentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilamentController.class)
public class FilamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilamentRepository filamentRepository;

    @Test
    public void getAllFilaments() throws Exception {
        Filament filament = new Filament("Test Brand", null, "Red", "ff0000", 200, 60, 70, 0.5f);

        when(filamentRepository.findAll()).thenReturn(Collections.singletonList(filament));

        mockMvc.perform(get("/api/v0/filaments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].manufacturer").value("Test Brand"));
    }

    @Test
    public void getFilamentById() throws Exception {
        Filament filament = new Filament("Test Brand", null, "Red", "ff0000", 200, 60, 70, 0.5f);

        when(filamentRepository.findById(1L)).thenReturn(Optional.of(filament));

        mockMvc.perform(get("/api/v0/filaments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.manufacturer").value("Test Brand"));
    }

    @Test
    public void getFilamentById_notFound() throws Exception {
        when(filamentRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v0/filaments/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createFilament() throws Exception {
        Filament filament = new Filament("Test Brand", null, "Red", "ff0000", 200, 60, 70, 0.5f);

        when(filamentRepository.save(any(Filament.class))).thenReturn(filament);

        mockMvc.perform(post("/api/v0/filaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"manufacturer\": \"Test Brand\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.manufacturer").value("Test Brand"));
    }

    @Test
    public void updateFilament() throws Exception {
        Filament filament = new Filament("Test Brand", null, "Red", "ff0000", 200, 60, 70, 0.5f);
        Filament updatedFilament = new Filament("Updated Brand", null, "Blue", "0000ff", 210, 70, 80, 0.6f);

        when(filamentRepository.findById(1L)).thenReturn(Optional.of(filament));
        when(filamentRepository.save(any(Filament.class))).thenReturn(updatedFilament);

        mockMvc.perform(put("/api/v0/filaments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"manufacturer\": \"Updated Brand\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.manufacturer").value("Updated Brand"));
    }

    @Test
    public void updateFilament_notFound() throws Exception {
        when(filamentRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v0/filaments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"manufacturer\": \"Updated Brand\" }"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteFilament() throws Exception {
        when(filamentRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/v0/filaments/1"))
                .andExpect(status().isNoContent());

        verify(filamentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteFilament_notFound() throws Exception {
        when(filamentRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/v0/filaments/1"))
                .andExpect(status().isNotFound());

        verify(filamentRepository, never()).deleteById(1L);
    }
}

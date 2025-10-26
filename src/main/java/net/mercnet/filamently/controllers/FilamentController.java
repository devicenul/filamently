package net.mercnet.filamently.controllers;

import com.google.common.collect.ImmutableList;
import net.mercnet.filamently.data.Filament;
import net.mercnet.filamently.data.FilamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v0/filaments")
public class FilamentController {

    private final FilamentRepository filamentRepository;

    @Autowired
    public FilamentController(FilamentRepository filamentRepository) {
        this.filamentRepository = filamentRepository;
    }

    @GetMapping
    public ImmutableList<Filament> getAllFilaments() {
        return ImmutableList.copyOf(filamentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filament> getFilamentById(@PathVariable Long id) {
        Optional<Filament> filament = filamentRepository.findById(id);
        return filament.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Filament createFilament(@RequestBody Filament filament) {
        return filamentRepository.save(filament);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filament> updateFilament(@PathVariable Long id, @RequestBody Filament filamentDetails) {
        Optional<Filament> filament = filamentRepository.findById(id);
        if (filament.isPresent()) {
            Filament updatedFilament = filament.get();
            updatedFilament.setManufacturer(filamentDetails.getManufacturer());
            updatedFilament.setMaterial(filamentDetails.getMaterial());
            updatedFilament.setColor(filamentDetails.getColor());
            updatedFilament.setRgba(filamentDetails.getRgba());
            updatedFilament.setExtruderTemp(filamentDetails.getExtruderTemp());
            updatedFilament.setBedTemp(filamentDetails.getBedTemp());
            updatedFilament.setBedTempFirstLayer(filamentDetails.getBedTempFirstLayer());
            updatedFilament.setRetraction(filamentDetails.getRetraction());
            return ResponseEntity.ok(filamentRepository.save(updatedFilament));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilament(@PathVariable Long id) {
        if (filamentRepository.existsById(id)) {
            filamentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

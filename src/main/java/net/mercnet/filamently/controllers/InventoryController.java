package net.mercnet.filamently.controllers;

import com.google.common.collect.ImmutableList;
import net.mercnet.filamently.data.FilamentRepository;
import net.mercnet.filamently.data.Inventory;
import net.mercnet.filamently.data.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v0/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;
    private final FilamentRepository filamentRepository;

    @Autowired
    public InventoryController(InventoryRepository inventoryRepository, FilamentRepository filamentRepository) {
        this.inventoryRepository = inventoryRepository;
        this.filamentRepository = filamentRepository;
    }

    @GetMapping
    public ImmutableList<Inventory> getAllInventory() {
        return ImmutableList.copyOf(inventoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        if (inventory.getFilament() == null || !filamentRepository.existsById(inventory.getFilament().getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(inventoryRepository.save(inventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventoryDetails) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isPresent()) {
            if (inventoryDetails.getFilament() == null || !filamentRepository.existsById(inventoryDetails.getFilament().getId())) {
                return ResponseEntity.badRequest().build();
            }
            Inventory updatedInventory = inventory.get();
            updatedInventory.setFilament(inventoryDetails.getFilament());
            updatedInventory.setPurchaseDate(inventoryDetails.getPurchaseDate());
            updatedInventory.setStartingWeight(inventoryDetails.getStartingWeight());
            updatedInventory.setUsedWeight(inventoryDetails.getUsedWeight());
            updatedInventory.setPricePerKg(inventoryDetails.getPricePerKg());
            return ResponseEntity.ok(inventoryRepository.save(updatedInventory));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

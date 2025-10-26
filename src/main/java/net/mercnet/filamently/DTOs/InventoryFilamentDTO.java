
package net.mercnet.filamently.DTOs;

import net.mercnet.filamently.data.Filament;
import net.mercnet.filamently.data.Inventory;

import java.time.LocalDate;

public final class InventoryFilamentDTO {

    private final Long inventoryId;
    private final LocalDate purchaseDate;
    private final Double startingWeight;
    private final Double usedWeight;
    private final Double pricePerKg;
    private final Filament filament;

    public InventoryFilamentDTO(Inventory inventory) {
        this.inventoryId = inventory.getId();
        this.purchaseDate = inventory.getPurchaseDate();
        this.startingWeight = inventory.getStartingWeight();
        this.usedWeight = inventory.getUsedWeight();
        this.pricePerKg = inventory.getPricePerKg();
        this.filament = inventory.getFilament();
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Double getStartingWeight() {
        return startingWeight;
    }

    public Double getUsedWeight() {
        return usedWeight;
    }

    public Double getPricePerKg() {
        return pricePerKg;
    }

    public Filament getFilament() {
        return filament;
    }
}

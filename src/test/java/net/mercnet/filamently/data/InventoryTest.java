package net.mercnet.filamently.data;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class InventoryTest {

    @Test
    public void testInventoryConstructorAndGetters() {
        Filament filament = new Filament("Manufacturer", Material.PLA, "Blue", "0000FF", 200, 60, 70, 0.8f);
        LocalDate purchaseDate = LocalDate.now();
        Inventory inventory = new Inventory(filament, purchaseDate, 1000.0, 100.0, 20.0);

        assertThat(inventory.getFilament()).isEqualTo(filament);
        assertThat(inventory.getPurchaseDate()).isEqualTo(purchaseDate);
        assertThat(inventory.getStartingWeight()).isEqualTo(1000.0);
        assertThat(inventory.getUsedWeight()).isEqualTo(100.0);
        assertThat(inventory.getPricePerKg()).isEqualTo(20.0);
    }
}

package net.mercnet.filamently.DTOs;

import net.mercnet.filamently.data.Filament;
import net.mercnet.filamently.data.Inventory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryFilamentDTOTest {

    @Test
    public void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        Filament filament = new Filament("Test Brand", null, "Red", "ff0000", 200, 60, 70, 0.5f);
        Inventory inventory = new Inventory(filament, LocalDate.now(), 1000.0, 0.0, 20.0);
        
        Field idField = Inventory.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(inventory, 1L);

        InventoryFilamentDTO dto = new InventoryFilamentDTO(inventory);

        assertEquals(inventory.getId(), dto.getInventoryId());
        assertEquals(inventory.getPurchaseDate(), dto.getPurchaseDate());
        assertEquals(inventory.getStartingWeight(), dto.getStartingWeight());
        assertEquals(inventory.getUsedWeight(), dto.getUsedWeight());
        assertEquals(inventory.getPricePerKg(), dto.getPricePerKg());
        assertEquals(inventory.getFilament(), dto.getFilament());
    }
}

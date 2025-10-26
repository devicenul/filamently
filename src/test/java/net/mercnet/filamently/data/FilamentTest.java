package net.mercnet.filamently.data;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class FilamentTest {

    @Test
    public void testFilamentConstructorAndGetters() {
        Filament filament = new Filament("Manufacturer", Material.PLA, "Blue", "0000ff", 200, 60, 70, 0.5f);
        assertThat(filament.getManufacturer()).isEqualTo("Manufacturer");
        assertThat(filament.getMaterial()).isEqualTo(Material.PLA);
        assertThat(filament.getColor()).isEqualTo("Blue");
        assertThat(filament.getRgba()).isEqualTo("0000ff");
        assertThat(filament.getExtruderTemp()).isEqualTo(200);
        assertThat(filament.getBedTemp()).isEqualTo(60);
        assertThat(filament.getBedTempFirstLayer()).isEqualTo(70);
        assertThat(filament.getRetraction()).isEqualTo(0.5f);
    }

    @Test
    public void testGetInventory() throws NoSuchFieldException, IllegalAccessException {
        Filament filament = new Filament("Manufacturer", Material.PLA, "Blue", "0000ff", 200, 60, 70, 0.5f);
        Field inventoryField = Filament.class.getDeclaredField("inventory");
        inventoryField.setAccessible(true);
        inventoryField.set(filament, new ArrayList<>());

        assertThat(filament.getInventory()).isNotNull();
        assertThat(filament.getInventory()).isInstanceOf(ImmutableList.class);
    }
}

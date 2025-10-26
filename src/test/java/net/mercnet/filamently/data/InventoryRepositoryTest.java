package net.mercnet.filamently.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InventoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InventoryRepository repository;

    @Test
    public void testSaveAndFind() {
        Filament filament = new Filament("Prusa", Material.PLA, "Galaxy Black", "000000", 215, 60, 60, 0.8f);
        entityManager.persist(filament);
        entityManager.flush();

        Inventory inventory = new Inventory(filament, LocalDate.now(), 1.0, 0.1, 25.0);
        entityManager.persist(inventory);
        entityManager.flush();

        Optional<Inventory> foundOptional = repository.findById(inventory.getId());
        assertThat(foundOptional).isPresent();
        Inventory found = foundOptional.get();

        assertThat(found.getFilament()).isEqualTo(inventory.getFilament());
        assertThat(found.getPurchaseDate()).isEqualTo(inventory.getPurchaseDate());
        assertThat(found.getStartingWeight()).isEqualTo(inventory.getStartingWeight());
        assertThat(found.getUsedWeight()).isEqualTo(inventory.getUsedWeight());
        assertThat(found.getPricePerKg()).isEqualTo(inventory.getPricePerKg());
    }

    @Test
    public void testFindAllWithFilament() {
        Filament filament = new Filament("Prusa", Material.PLA, "Jet Black", "000000", 215, 60, 60, 0.8f);
        entityManager.persist(filament);
        entityManager.flush();

        Inventory inventory = new Inventory(filament, LocalDate.now(), 1.0, 0.0, 30.0);
        entityManager.persist(inventory);
        entityManager.flush();

        List<Inventory> inventoryList = repository.findAllWithFilament();

        assertThat(inventoryList).hasSize(1);
        assertThat(inventoryList.get(0).getFilament()).isNotNull();
        assertThat(inventoryList.get(0).getFilament().getManufacturer()).isEqualTo("Prusa");
    }
}

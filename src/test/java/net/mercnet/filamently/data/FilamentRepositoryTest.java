package net.mercnet.filamently.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FilamentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FilamentRepository repository;

    @Test
    public void testFindByManufacturer() {
        Filament filament = new Filament("Prusa", Material.PLA, "Galaxy Black", "000000", 215, 60, 60, 0.5f);
        entityManager.persist(filament);
        entityManager.flush();

        Filament found = repository.findByManufacturer(filament.getManufacturer()).get(0);

        assertThat(found.getManufacturer()).isEqualTo(filament.getManufacturer());
    }
}

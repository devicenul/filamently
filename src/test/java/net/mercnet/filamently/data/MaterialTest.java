package net.mercnet.filamently.data;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MaterialTest {

    @Test
    public void testMaterialEnum() {
        assertThat(Material.PLA.name()).isEqualTo("PLA");
        assertThat(Material.PETG.name()).isEqualTo("PETG");
        assertThat(Material.ASA.name()).isEqualTo("ASA");
        assertThat(Material.ABS.name()).isEqualTo("ABS");
    }
}

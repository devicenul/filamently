package net.mercnet.filamently.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Query("SELECT i FROM Inventory i JOIN FETCH i.filament")
    List<Inventory> findAllWithFilament();
}

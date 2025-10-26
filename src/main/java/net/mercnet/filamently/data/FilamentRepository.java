package net.mercnet.filamently.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilamentRepository extends CrudRepository<Filament, Long> {
    List<Filament> findByManufacturer(String manufacturer);
}

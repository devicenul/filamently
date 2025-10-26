package net.mercnet.filamently.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Filament filament;

    private LocalDate purchaseDate;
    private Double startingWeight;
    private Double usedWeight;
    private Double pricePerKg;

    protected Inventory() {}

    public Inventory(Filament filament, LocalDate purchaseDate, Double startingWeight, Double usedWeight, Double pricePerKg) {
        this.filament = filament;
        this.purchaseDate = purchaseDate;
        this.startingWeight = startingWeight;
        this.usedWeight = usedWeight;
        this.pricePerKg = pricePerKg;
    }

    public Long getId() {
        return id;
    }

    public Filament getFilament() {
        return filament;
    }

    public void setFilament(Filament filament) {
        this.filament = filament;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(Double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public Double getUsedWeight() {
        return usedWeight;
    }

    public void setUsedWeight(Double usedWeight) {
        this.usedWeight = usedWeight;
    }

    public Double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(Double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }
}

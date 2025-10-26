package net.mercnet.filamently.data;

import com.google.common.collect.ImmutableList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Filament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String manufacturer;
    private Material material;
    private String color;
    private String rgba;
    private Integer extruderTemp;
    private Integer bedTemp;
    private Integer bedTempFirstLayer;
    private Float retraction;

    @OneToMany(mappedBy = "filament", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Inventory> inventory = new ArrayList<>();

    protected Filament() {}

    public Filament(String manufacturer, Material material, String color, String rgba, Integer extruderTemp, Integer bedTemp, Integer bedTempFirstLayer, Float retraction) {
        this.manufacturer = manufacturer;
        this.material = material;
        this.color = color;
        this.rgba = rgba;
        this.extruderTemp = extruderTemp;
        this.bedTemp = bedTemp;
        this.bedTempFirstLayer = bedTempFirstLayer;
        this.retraction = retraction;
    }

    @Override
    public String toString() {
        return String.format(
                "Filament[id=%d, manufacturer='%s', material='%s', color='%s', rgba='%s', extruderTemp=%d, bedTemp=%d, bedTempFirstLayer=%d, retraction=%f]",
                id, manufacturer, material, color, rgba, extruderTemp, bedTemp, bedTempFirstLayer, retraction);
    }

    public List<Inventory> getInventory() {
        return ImmutableList.copyOf(inventory);
    }

    public Long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRgba() {
        return rgba;
    }

    public void setRgba(String rgba) {
        this.rgba = rgba;
    }

    public Integer getExtruderTemp() {
        return extruderTemp;
    }

    public void setExtruderTemp(Integer extruderTemp) {
        this.extruderTemp = extruderTemp;
    }

    public Integer getBedTemp() {
        return bedTemp;
    }

    public void setBedTemp(Integer bedTemp) {
        this.bedTemp = bedTemp;
    }

    public Integer getBedTempFirstLayer() {
        return bedTempFirstLayer;
    }

    public void setBedTempFirstLayer(Integer bedTempFirstLayer) {
        this.bedTempFirstLayer = bedTempFirstLayer;
    }

    public Float getRetraction() {
        return retraction;
    }

    public void setRetraction(Float retraction) {
        this.retraction = retraction;
    }
}

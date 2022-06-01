package ingis.microgreenappapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Table(name = "Tray")
public class Tray {

    @Id
    @GeneratedValue
    private Integer trayId;

    @NotBlank
    @Size(max = 50, message = "Name too long!")
    private String trayType;
    private String size;
    private Double qty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="orderDetailsId")
    private OrderDetails orderDetails;

    @OneToOne(mappedBy = "tray")
    private PlantingMedium plantingMedium;

    public Tray() {
    }

    public Tray(String trayType, String size, Double qty, PlantingMedium plantingMedium) {
        this.trayType = trayType;
        this.size = size;
        this.qty = qty;
        this.plantingMedium = plantingMedium;
    }

    public String getTrayType() {
        return trayType;
    }

    public void setTrayType(String trayType) {
        this.trayType = trayType;
    }

    public Integer getTrayId() {
        return trayId;
    }

    public void setTrayId(Integer trayId) {
        this.trayId = trayId;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    @JsonIgnore
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }


    public PlantingMedium getPlantingMedium() {
        return plantingMedium;
    }

    public void setPlantingMedium(PlantingMedium plantingMedium) {
        this.plantingMedium = plantingMedium;
    }

    @Override
    public String toString() {
        return "Tray{" +
                "trayType='" + trayType + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tray tray = (Tray) o;
        return trayId == tray.trayId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trayId);
    }
}

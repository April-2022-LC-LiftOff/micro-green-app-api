package ingis.microgreenappapi.models;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "Tray")
public class Tray {
    @Id
    @GeneratedValue
    private int trayId;
    private static int nextId = 1;

    private String trayType;
    private String size;
    private Double qty;

    @ManyToOne
    @JoinColumn(name="orderId")
    private CustomerOrder customerOrder;

    @OneToOne(mappedBy = "tray")
    private PlantingMedium plantingMedium;

    public Tray() {
    }

    public Tray(int trayId, String trayType, String size, Double qty, CustomerOrder customerOrder, PlantingMedium plantingMedium) {
        this.trayId = trayId;
        this.trayType = trayType;
        this.size = size;
        this.qty = qty;
        this.customerOrder = customerOrder;
        this.plantingMedium = plantingMedium;
    }

    public int getTrayId() {
        return trayId;
    }

    public void setTrayId(int trayId) {
        this.trayId = trayId;
    }

    public String getTrayType() {
        return trayType;
    }

    public void setTrayType(String trayType) {
        this.trayType = trayType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Number getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }


    public PlantingMedium getPlantingMedium() {
        return plantingMedium;
    }

    public void setPlantingMedium(PlantingMedium plantingMedium) {
        this.plantingMedium = plantingMedium;
    }
}

package ingis.microgreenappapi.models;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;
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
    @JoinColumn(name="orderDetailsId")
    private OrderDetails orderDetails;

    @OneToOne(mappedBy = "tray")
    private PlantingMedium plantingMedium;

    public Tray() {
    }

    public Tray(int trayId, String trayType, String size, Double qty, OrderDetails orderDetails, PlantingMedium plantingMedium) {
        this.trayId = trayId;
        this.trayType = trayType;
        this.size = size;
        this.qty = qty;
        this.orderDetails = orderDetails;
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

//    public OrderDetails getOrderDetails() {
//        return orderDetails;
//    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public PlantingMedium getPlantingMedium() {
        return plantingMedium;
    }

    public void setPlantingMedium(PlantingMedium plantingMedium) {
        this.plantingMedium = plantingMedium;
    }
}

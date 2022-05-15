package ingis.microgreenappapi.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Planting_Medium")
public class PlantingMedium {
    @Id
    @GeneratedValue
    private int mediumId;
    private static int nextId = 1;

    private String mediumType;

    private Double qty;

    @OneToOne
    @JoinColumn(name = "trayId")
    private Tray tray;

    @ManyToOne
    @JoinColumn(name="orderDetailsId")
    private OrderDetails orderDetails;


    public PlantingMedium() {
    }

    public PlantingMedium(int mediumId, String mediumType, Double qty, Tray tray, OrderDetails orderDetails) {
        this.mediumId = mediumId;
        this.mediumType = mediumType;
        this.qty = qty;
        this.tray = tray;
        this.orderDetails = orderDetails;
    }

    public int getMediumId() {
        return mediumId;
    }

    public void setMediumId(int mediumId) {
        this.mediumId = mediumId;
    }

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

//    public Tray getTray() {
//        return tray;
//    }

    public void setTray(Tray tray) {
        this.tray = tray;
    }

//    public OrderDetails getOrderDetails() {
//        return orderDetails;
//    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}


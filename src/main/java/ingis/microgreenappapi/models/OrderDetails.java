package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orderDetails")
public class OrderDetails {

    @Id
    @GeneratedValue
    private int orderDetailsId;
    private static int nextId =1;

    @Column(name = "Qty")
    private int qty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seedId")
    private Seed seed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="trayId")
    private Tray tray;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mediumId")
    private PlantingMedium plantingMedium;

    @ManyToOne
    private CustomerOrder customerOrder;

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public Tray getTray() {
        return tray;
    }

    public void setTray(Tray tray) {
        this.tray = tray;
    }

    public PlantingMedium getPlantingMedium() {
        return plantingMedium;
    }

    public void setPlantingMedium(PlantingMedium plantingMedium) {
        this.plantingMedium = plantingMedium;
    }

    //    public CustomerOrder getCustomerOrder() {
//        return customerOrder;
//    }
//
//    public void setCustomerOrder(CustomerOrder customerOrder) {
//        this.customerOrder = customerOrder;
//    }
}

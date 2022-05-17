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

    @OneToMany(mappedBy = "orderDetails", fetch = FetchType.LAZY)
    private List<Seed> seed = new ArrayList<>();

    @OneToMany(mappedBy = "orderDetails", fetch = FetchType.LAZY)
    private List<Tray> tray = new ArrayList<>();

    @OneToMany(mappedBy = "orderDetails", fetch = FetchType.LAZY)
    private List<PlantingMedium> plantingMedium = new ArrayList<>();

//    @OneToOne(mappedBy = "orderDetails", fetch = FetchType.LAZY)
//    private CustomerOrder customerOrder;

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

    public List<Seed> getSeed() {
        return seed;
    }

    public void setSeed(List<Seed> seed) {
        this.seed = seed;
    }

    public List<Tray> getTray() {
        return tray;
    }

    public void setTray(List<Tray> tray) {
        this.tray = tray;
    }

    public List<PlantingMedium> getPlantingMedium() {
        return plantingMedium;
    }

    public void setPlantingMedium(List<PlantingMedium> plantingMedium) {
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

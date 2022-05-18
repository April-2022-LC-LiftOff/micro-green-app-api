package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import java.util.Objects;

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
    @JoinColumn(name = "orderId")
    private CustomerOrder customerOrder;

    public OrderDetails() {
    }

    public OrderDetails(Number orderDetailsId, Integer qty, Seed seedId, Tray trayId) {
        this.qty = qty;
        this.seed = seedId;
        this.tray = trayId;
        this.orderDetailsId = nextId;
        nextId++;
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Seed getSeedId() {
        return seed;
    }

    public void setSeedId(Seed seedId) {
        this.seed = seedId;
    }

    public Tray getTrayId() {
        return tray;
    }

    public void setTrayId(Tray trayId) {
        this.tray = trayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return orderDetailsId == that.orderDetailsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailsId);
    }
}

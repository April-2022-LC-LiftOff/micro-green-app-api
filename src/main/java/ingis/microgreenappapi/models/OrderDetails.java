package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

@Entity
@Table(name = "orderDetails")
public class OrderDetails {

    @Id
    @GeneratedValue
    private Integer orderDetailsId;

    @Column(name = "Qty")
    private int qty;

    @OneToOne
    @JoinColumn(name = "seedId")
    private Seed seed;

    @OneToOne
    @JoinColumn(name="trayId")
    private Tray tray;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private CustomerOrder customerOrder;

    public OrderDetails() {
    }


    public OrderDetails(int qty, Seed seed, Tray tray, CustomerOrder customerOrder) {
        this.qty = qty;
        this.seed = seed;
        this.tray = tray;
        this.customerOrder = customerOrder;
    }

    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
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

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
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

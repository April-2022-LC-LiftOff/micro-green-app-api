package ingis.microgreenappapi.models;




import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderDetails")
public class OrderDetails {

    @Id
    @GeneratedValue
    private int orderDetailsId;
    private static int nextId =1;

    private Integer qty;

    @OneToOne
    @JoinColumn(name = "seedId")
    private Seed seed;

    @OneToOne
    @JoinColumn(name = "trayId")
    private Tray tray;

    @ManyToOne
    @JoinColumn(name = "customer_order_order_id")
    private CustomerOrder customerOrder;

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

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

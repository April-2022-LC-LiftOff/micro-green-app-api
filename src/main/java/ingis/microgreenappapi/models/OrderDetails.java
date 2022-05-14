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

   private Number qty;

    @ManyToMany
    @JoinColumn(name = "seedId")
    private Seed seed;

    @ManyToMany
    @JoinColumn(name = "trayId")
    private Tray tray;

    public OrderDetails() {
    }

    public OrderDetails(Number orderDetailsId, Number qty, Seed seedId, Tray trayId) {
        this.qty = qty;
        this.seed = seedId;
        this.tray = trayId;
        this.orderDetailsId = nextId;
        nextId++;
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public Number getQty() {
        return qty;
    }

    public void setQty(Number qty) {
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

package ingis.microgreenappapi.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue
    private int orderDetailsId;

    private Integer qty;
    private Integer seedRefId;

    public OrderDetails(Integer qty, Integer seedRefId) {
        this.qty = qty;
        this.seedRefId = seedRefId;
    }

    public OrderDetails() {
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getSeedRefId() {
        return seedRefId;
    }

    public void setSeedRefId(Integer seedRefId) {
        this.seedRefId = seedRefId;
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsId=" + orderDetailsId +
                ", qty=" + qty +
                ", seedRefId=" + seedRefId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails)) return false;

        OrderDetails that = (OrderDetails) o;

        return getOrderDetailsId() == that.getOrderDetailsId();
    }

    @Override
    public int hashCode() {
        return getOrderDetailsId();
    }
}

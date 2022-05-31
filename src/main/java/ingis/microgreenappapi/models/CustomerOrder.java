package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.*;

@Entity
//@Table(name="customer_orders")
public class CustomerOrder {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue
    private int orderId;

    private Boolean activeOrder;

// one to many unidirectional mapping
// default fetch type for OneToMany: LAZY
//    @OneToMany(cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            mappedBy = "customerOrder")
@OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
@JoinColumn(name = "orderId")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public CustomerOrder(Boolean activeOrder, List<OrderDetails> orderDetails) {
        this.activeOrder = activeOrder;
        this.orderDetails = orderDetails;
    }

    public CustomerOrder() {
    }

    public int getOrderId() {
        return orderId;
    }

    public Boolean getActiveOrder() {
        return activeOrder;
    }

    public void setActiveOrder(Boolean activeOrder) {
        this.activeOrder = activeOrder;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void add(OrderDetails details) {
        if (details != null) {
            if (orderDetails == null) {
                orderDetails = new ArrayList<>();
            }

            orderDetails.add(details);
        }
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                 ", activeOrder=" + activeOrder +
                ", orderDetails=" + orderDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}

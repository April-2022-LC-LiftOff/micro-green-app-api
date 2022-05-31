package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="customer_orders")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @OneToMany
    private int orderId;
//    private static int nextId = 1;
//
//    private Type customerId;
    private Date orderDate;
    private Date deliveryDate;
    private Boolean activeOrder;
//
// one to many unidirectional mapping
// default fetch type for OneToMany: LAZY
    @OneToMany
    private Set<OrderDetails> orderDetails = new HashSet<>();

    public CustomerOrder(Date orderDate, Date deliveryDate, Boolean activeOrder, Set<OrderDetails> orderDetails) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.activeOrder = activeOrder;
        this.orderDetails = orderDetails;
    }

    public CustomerOrder() {
    }

    //
//    public CustomerOrder(Type customerId, Date orderDate, Date deliveryDate, Boolean activeOrder, ingis.microgreenappapi.models.OrderDetails[] orderDetails) {
//        this.customerId = customerId;
//        this.orderDate = orderDate;
//        this.deliveryDate = deliveryDate;
//        this.activeOrder = activeOrder;
//        OrderDetails = orderDetails;
//        this.orderId = nextId;
//        nextId++;
//
//    }

    public int getOrderId() {
        return orderId;
    }
//
//    public Type getCustomerId() {
//        return customerId;
//    }
//    public void setCustomerId(Type customerId) {
//        this.customerId = customerId;
//    }
//
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getActiveOrder() {
        return activeOrder;
    }

    public void setActiveOrder(Boolean activeOrder) {
        this.activeOrder = activeOrder;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void add(OrderDetails details) {
        if (details != null) {
            if (orderDetails == null) {
                orderDetails = new HashSet<>();
            }

            orderDetails.add(details);
        }
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
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

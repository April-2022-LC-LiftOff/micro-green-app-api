package ingis.microgreenappapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Objects;

//@Entity
public class CustomerOrder {

//    @Id
//    @GeneratedValue
////    @OneToMany
//    private int orderId;
//    private static int nextId = 1;
//
//    private Type customerId;
//    private Date orderDate;
//    private Date deliveryDate;
//    private Boolean activeOrder;
//
////    @OneToMany
//    private OrderDetails OrderDetails[];
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
//
//    public int getOrderId() {
//        return orderId;
//    }
//
//    public Type getCustomerId() {
//        return customerId;
//    }
//    public void setCustomerId(Type customerId) {
//        this.customerId = customerId;
//    }
//
//    public Date getOrderDate() {
//        return orderDate;
//    }
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public Date getDeliveryDate() {
//        return deliveryDate;
//    }
//    public void setDeliveryDate(Date deliveryDate) {
//        this.deliveryDate = deliveryDate;
//    }
//
//    public ingis.microgreenappapi.models.OrderDetails[] getOrderDetails() {
//        return OrderDetails;
//    }
//    public void setOrderDetails(ingis.microgreenappapi.models.OrderDetails[] orderDetails) {
//        OrderDetails = orderDetails;
//    }
//
//    public Boolean getActiveOrder() {
//        return activeOrder;
//    }
//    public void setActiveOrder(Boolean activeOrder) {
//        this.activeOrder = activeOrder;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CustomerOrder that = (CustomerOrder) o;
//        return orderId == that.orderId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(orderId);
//    }
}

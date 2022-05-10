package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "customer_orders")
public class CustomerOrder {

    @Id
    @GeneratedValue
//    @OneToMany
    private int orderId;
    private static int nextId = 1;

    private Date orderDate;
    private Date deliveryDate;
    private Boolean activeOrder;

    private  Customer customer;
    @OneToMany
    private OrderDetails orderDetails;

    public CustomerOrder(int orderId, Date orderDate, Date deliveryDate, Boolean activeOrder, Customer customer, OrderDetails orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.activeOrder = activeOrder;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public  CustomerOrder(){

    }

    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "order_date")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "delivery_date")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Column(name = "active_order")
    public Boolean getActiveOrder() {
        return activeOrder;
    }

    public void setActiveOrder(Boolean activeOrder) {
        this.activeOrder = activeOrder;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "customer_order_customer_FK"))
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany
    @JoinColumn(name = "order_details", nullable = false, foreignKey = @ForeignKey(name = "customer_order_orderdetail_FK"))
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" + "orderId =" + orderId +
                ", customer = " + customer +
                ", orderDate = " + orderDate +
                ", deliveryDate =" + deliveryDate +
                ", activeOrder = " + activeOrder +
                ", orderDetails = " + orderDetails +
                '}';
    }

}

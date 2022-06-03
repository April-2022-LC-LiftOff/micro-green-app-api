package ingis.microgreenappapi.models;

import java.time.LocalDate;
import java.util.List;

public class OrderRequest {

    private String customerName;
    private String seedName;
    private Double qty;
    private LocalDate orderDate;
    private LocalDate deliveryDate;

    private List<OrderDetails> orderDetails;
    private Customer customer;

    public OrderRequest(){

    }

    public OrderRequest(String customerName, String seedName, Double qty, LocalDate orderDate, LocalDate deliveryDate, List<OrderDetails> orderDetails, Customer customer) {
        this.customerName = customerName;
        this.seedName = seedName;
        this.qty = qty;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderDetails = orderDetails;
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

package ingis.microgreenappapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "customer_orders")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private static int nextId = 1;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "active_order")
    private Boolean activeOrder;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customerId")
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.LAZY)
    private List<Seed> seed = new ArrayList<>();

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.LAZY)
    private List<Tray> tray = new ArrayList<>();

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.LAZY)
    private List<PlantingMedium> plantingMedium = new ArrayList<>();

    public CustomerOrder() {

    }

    public CustomerOrder(int orderId, LocalDate orderDate, LocalDate deliveryDate, Boolean activeOrder, Customer customer, List<Seed> seed, List<Tray> tray, List<PlantingMedium> plantingMedium) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.activeOrder = activeOrder;
        this.customer = customer;
        this.seed = seed;
        this.tray = tray;
        this.plantingMedium = plantingMedium;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public Boolean getActiveOrder() {
        return activeOrder;
    }

    public void setActiveOrder(Boolean activeOrder) {
        this.activeOrder = activeOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public Seed getSeed() {
//        return seed;
//    }
//
//    public void setSeed(Seed seed) {
//        this.seed = seed;
//    }


    public List<Seed> getSeed() {
        return seed;
    }

    public void setSeed(List<Seed> seed) {
        this.seed = seed;
    }

//    public Tray getTray() {
//        return tray;
//    }
//
//    public void setTray(Tray tray) {
//        this.tray = tray;
//    }
//
//    public PlantingMedium getPlantingMedium() {
//        return plantingMedium;
//    }
//
//    public void setPlantingMedium(PlantingMedium plantingMedium) {
//        this.plantingMedium = plantingMedium;
//    }


    public List<Tray> getTray() {
        return tray;
    }

    public void setTray(List<Tray> tray) {
        this.tray = tray;
    }

    public List<PlantingMedium> getPlantingMedium() {
        return plantingMedium;
    }

    public void setPlantingMedium(List<PlantingMedium> plantingMedium) {
        this.plantingMedium = plantingMedium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return orderId == that.orderId && Objects.equals(orderDate, that.orderDate) && Objects.equals(deliveryDate, that.deliveryDate) && Objects.equals(activeOrder, that.activeOrder) && Objects.equals(customer, that.customer) && Objects.equals(seed, that.seed) && Objects.equals(tray, that.tray) && Objects.equals(plantingMedium, that.plantingMedium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, deliveryDate, activeOrder, customer, seed, tray, plantingMedium);
    }
}

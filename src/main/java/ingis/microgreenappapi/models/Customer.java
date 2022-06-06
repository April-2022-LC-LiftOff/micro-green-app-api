package ingis.microgreenappapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

//    @NotBlank
    @Size(max = 50, message = "Name too long!")
    @Column(name = "customer_name")
    private String customerName;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CustomerOrder> orders = new ArrayList<>();

    public Customer() {

    }

//    public Customer(int customerId, String customerName, List<CustomerOrder> customerOrder) {
//        this.customerId = nextId;
//        nextId++;
//        this.customerName = customerName;
//        this.customerOrder = customerOrder;
//    }


    public Customer(String customerName, List<CustomerOrder> orders) {
        this.customerName = customerName;
        this.orders = orders;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public  Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    @JsonIgnore
    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    public void addOrder(CustomerOrder order){
       this.orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(CustomerOrder order){
        this.orders.remove(order);
        order.setCustomer(null);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}

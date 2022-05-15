package ingis.microgreenappapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue
    private int customerId;
    private static int nextId = 1;

    //    @Size (max = 50, message = "Name too long!")
    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrder = new ArrayList<>();

    public Customer() {
    }

    public Customer(int customerId, String customerName, List<CustomerOrder> customerOrder) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerOrder = customerOrder;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    //removed getter for customerOrder to prevent infinite recursion
//    public List<CustomerOrder> getCustomerOrder() {
//        return customerOrder;
//    }

    public void setCustomerOrder(List<CustomerOrder> customerOrder) {
        this.customerOrder = customerOrder;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerOrder=" + customerOrder +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(customerName, customer.customerName) && Objects.equals(customerOrder, customer.customerOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerOrder);
    }
}

package ingis.microgreenappapi.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private List<CustomerOrder> customerOrder;

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

    public List<CustomerOrder> getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(List<CustomerOrder> customerOrder) {
        this.customerOrder = customerOrder;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
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

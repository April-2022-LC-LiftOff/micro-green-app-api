package ingis.microgreenappapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @NotNull(message = "Name is required")
    @Size(max = 50, message = "Name too long!")
    @Column(name = "customer_name")
    private String customerName;

    private boolean activeCustomer = true;


    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrder = new ArrayList<>();

    public Customer() {

    }

    public Customer(String customerName, boolean activeCustomer, List<CustomerOrder> customerOrder) {
        this.customerName = customerName;
        this.activeCustomer = activeCustomer;
        this.customerOrder = customerOrder;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isActiveCustomer() {
        return activeCustomer;
    }

    public void setActiveCustomer(boolean activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    public Integer getCustomerId() {
        return customerId;
    }


    @JsonIgnore
    public List<CustomerOrder> getCustomerOrder() {
        return customerOrder;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", activeCustomer=" + activeCustomer +
                ", customerOrder=" + customerOrder +
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

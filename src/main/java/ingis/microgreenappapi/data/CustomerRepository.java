package ingis.microgreenappapi.data;
import ingis.microgreenappapi.models.Customer;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepositoryImplementation<Customer, Integer> {
    Customer findByCustomerName(String customerName);
}

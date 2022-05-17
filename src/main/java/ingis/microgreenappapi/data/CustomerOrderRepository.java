package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

}

package ingis.microgreenappapi.repositories;

import ingis.microgreenappapi.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

}

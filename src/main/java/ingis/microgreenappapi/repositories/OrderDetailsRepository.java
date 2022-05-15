package ingis.microgreenappapi.repositories;

import ingis.microgreenappapi.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}

package ingis.microgreenappapi;

import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroGreenAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroGreenAppApiApplication.class, args);

	}
	@Autowired
	private CustomerOrderRepository customerOrderRepository;

}

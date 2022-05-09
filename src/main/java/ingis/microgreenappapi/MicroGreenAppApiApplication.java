package ingis.microgreenappapi;

import ingis.microgreenappapi.controllers.InventoryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class MicroGreenAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroGreenAppApiApplication.class, args);
		// Create a HashMap object called capitalCities
		HashMap<String, String> capitalCities = new HashMap<String, String>();

		// Add keys and values (Country, City)
		capitalCities.put("England", "London");
		capitalCities.put("Germany", "Berlin");
		capitalCities.put("Norway", "Oslo");
		capitalCities.put("USA", "Washington DC");
		System.out.println(capitalCities);
	}

}

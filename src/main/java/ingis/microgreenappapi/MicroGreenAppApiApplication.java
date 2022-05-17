package ingis.microgreenappapi;

import ingis.microgreenappapi.controllers.InventoryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.HashMap;

@SpringBootApplication
public class MicroGreenAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroGreenAppApiApplication.class, args);
		// Take a date
		LocalDate date = LocalDate.parse("2016-05-03");
		// Displaying date
		System.out.println("Date : "+date);
		// subtract days to date
		LocalDate newDate = date.minusDays(5);
		System.out.println("New Date : "+newDate);
	}


}

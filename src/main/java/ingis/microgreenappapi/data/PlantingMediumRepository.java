package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.PlantingMedium;
import ingis.microgreenappapi.models.Seed;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlantingMediumRepository extends JpaRepository<PlantingMedium, Integer> {

}
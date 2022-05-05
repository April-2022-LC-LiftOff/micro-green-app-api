package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.PlantingMedium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantingMediumRepository extends JpaRepositoryImplementation<PlantingMedium, Integer> {

}
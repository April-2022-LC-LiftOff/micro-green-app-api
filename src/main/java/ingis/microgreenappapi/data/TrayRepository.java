package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.Tray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrayRepository extends JpaRepository<Tray, Integer> {

}

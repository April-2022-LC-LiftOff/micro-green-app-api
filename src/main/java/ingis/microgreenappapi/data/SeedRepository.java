package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.Seed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeedRepository extends JpaRepository<Seed, Integer> {

}


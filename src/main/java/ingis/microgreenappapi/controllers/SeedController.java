package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SeedController {

    @Autowired
    private SeedRepository seedRepo;

    // view all seed information

    @GetMapping("/seeds")
    public List<Seed> getSeeds() {
        return seedRepo.findAll();
    }


}



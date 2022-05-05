package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/seeds")
public class SeedController {

    @Autowired
    private SeedRepository seedRepo;

    // view all seed information

    @GetMapping
    public List<Seed> getSeeds() {
        return seedRepo.findAll();
    }

    @PostMapping(value = "/add")
    public String addSeeds(@RequestBody Seed seed) {
        seedRepo.save(seed);
        return "Saved....";
    }

}



package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/seeds")
@CrossOrigin(origins = "*")
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

    @PutMapping(value = "/update/{seedId}")
    public String updateSeed(@PathVariable(value = "seedId") Integer seedId, @RequestBody Seed seed) {
        Seed updatedSeed = seedRepo.findById(seedId).get();
        updatedSeed.setSeedName(seed.getSeedName());
        updatedSeed.setSeedingDensity(seed.getSeedingDensity());
        updatedSeed.setSeedPresoak(seed.getSeedPresoak());
        updatedSeed.setBlackoutTime(seed.getBlackoutTime());
        updatedSeed.setQty(seed.getQty());

        seedRepo.save(updatedSeed);
        return "updated....";
    }

    @DeleteMapping(value = "/delete/{seedId}")
    public String deleteSeed(@PathVariable Integer seedId) {
        Seed deletedSeed = seedRepo.findById(seedId).get();
        seedRepo.delete(deletedSeed);
        return "deleted...";
    }



}



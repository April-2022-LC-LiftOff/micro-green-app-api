package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class SeedController {




    // Seed Inventory
    // View seedName and qty
    //SeedData.
    // Modify seedName qty
    // Update from Orders seedName qty


    @Autowired
    private SeedRepository seedRepo;

    // Seed Data
    // View Seed Data info
    // SeedData.getAll()
    // Convert to JSON
    // Return to /seed
    // view all seed information
    @GetMapping("/seeds")
    public List<Seed> getSeeds() {
        return seedRepo.findAll();
    }


    // Add new Seed Data
    //SeedData.add(newSeed)
    // Save seed info
    @PostMapping(value = "/save")
    public String saveSeed(Seed seed) {
        seedRepo.save(seed);
        return "Saved....";
    }



    // Modify Seed Data info
    //SeedData.
    // Update seed info
    @PutMapping(value = "update/{id}")
    public String updateSeed(@PathVariable Integer id, @RequestBody Seed seed) {
        Seed updatedSeed = seedRepo.findById(id).get();
        updatedSeed.setSeedName(seed.getSeedName());
        updatedSeed.setSeedingDensity(seed.getSeedingDensity());
        updatedSeed.setSeedPresoak(seed.getSeedPresoak());
        updatedSeed.setBlackoutTime(seed.getBlackoutTime());
        updatedSeed.setHarvestTime(seed.getHarvestTime());
        seedRepo.save(updatedSeed);
        return "Updated.....";
    }


    // Delete Seed Data
    //SeedData.getId.growing=false
    //Delete Seed
    @DeleteMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        Seed deleteSeed = seedRepo.findById(id).get();
        seedRepo.delete(deleteSeed);
        return "Deleted......";
    }

}



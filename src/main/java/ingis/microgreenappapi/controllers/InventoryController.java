package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.models.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private static SeedRepository seedRepo;

//    public static void checkInventory(int seedId, int qty) {
//        System.out.println("Seed " + seedId);
////        int currentSeedQty = seedRepo.findById(seedId).get().getQty();
////        int orderedSeedQty = qty * seedRepo.findById(seedId).get().getSeedingDensity();
////        if (currentSeedQty > orderedSeedQty) {
////            return true;
////        } else {
////            return false;
////        }
//    }

//    public static void updateInventorySeedQty(int seedId, int qty) {
//        System.out.println(seedId + "qty " + qty);
////        Seed updatedSeed = seedRepo.findById(2).
////        updatedSeed.setQty(seedRepo.findById(seedId).get().getQty() -
////                (qty * seedRepo.findById(seedId).get().getSeedingDensity()));
////        seedRepo.save(updatedSeed);
//    }

    // **** view all seed information
    @GetMapping
    public List<Seed> viewInventory() {
        return seedRepo.findAll();
    }

// **** add new seed to inventory
    @PostMapping(value = "/add")
    public String addSeedsToInventory(@RequestBody Seed seed) {
        seedRepo.save(seed);
        return "Saved....";
    }

// **** view seed all info by seedId
    @GetMapping(value = "/{seedId}")
    public Seed  viewSeedInfo(@PathVariable(value = "seedId") Integer seedId) {
        //todo add exception handling
        return seedRepo.findById(seedId).get();
    }

// **** update inventory seed qty
    @PutMapping(value = "/update/{seedId}")
    public Integer updateSeed(@PathVariable(value = "seedId") Integer seedId, @RequestBody Seed seed) {
        //todo add exception handling
        Seed updatedSeed = seedRepo.findById(seedId).get();
        updatedSeed.setSeedName(seed.getSeedName());
        updatedSeed.setQty(seed.getQty() + seedRepo.findById(seedId).get().getQty());
        seedRepo.save(updatedSeed);
        return (updatedSeed.getQty());
    }

    @DeleteMapping(value = "/delete/{seedId}")
    public String deleteSeed(@PathVariable Integer seedId) {
        //todo add exception handling
        Seed deletedSeed = seedRepo.findById(seedId).get();
        seedRepo.delete(deletedSeed);
        return "deleted...";
    }


}


package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.data.TrayRepository;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.Tray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trays")
public class TrayController {

    @Autowired
    private TrayRepository trayRepo;

    // view all tray information

    @GetMapping
    public List<Tray> getTrays() {
        return trayRepo.findAll();
    }

    @PostMapping(value = "/add")
    public String addTrays(@RequestBody Tray tray) {
        trayRepo.save(tray);
        return "Saved....";
    }

//    @PutMapping(value = "/update/{seedId}")
//    public String updateSeed(@PathVariable(value = "seedId") Integer seedId, @RequestBody Seed seed) {
//        Seed updatedSeed = seedRepo.findById(seedId).get();
//        updatedSeed.setSeedName(seed.getSeedName());
//        updatedSeed.setSeedingDensity(seed.getSeedingDensity());
//        updatedSeed.setSeedPresoak(seed.getSeedPresoak());
//        updatedSeed.setBlackoutTime(seed.getBlackoutTime());
//        updatedSeed.setQty(seed.getQty());
//
//        seedRepo.save(updatedSeed);
//        return "updated....";
//    }
//
//    @DeleteMapping(value = "/delete/{seedId}")
//    public String deleteSeed(@PathVariable Integer seedId) {
//        Seed deletedSeed = seedRepo.findById(seedId).get();
//        seedRepo.delete(deletedSeed);
//        return "deleted...";
//    }




}

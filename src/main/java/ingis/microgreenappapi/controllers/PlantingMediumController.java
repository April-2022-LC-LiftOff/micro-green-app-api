package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.PlantingMediumRepository;
import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.PlantingMedium;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.Tray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medium")
public class PlantingMediumController {

    @Autowired
    private PlantingMediumRepository mediumRepo;

    // view all planting medium information

    @GetMapping
    public List<PlantingMedium> getMedium() {
        return mediumRepo.findAll();
    } @PostMapping(value = "/add")
    public String addTrays(@RequestBody PlantingMedium medium) {
        mediumRepo.save(medium);
        return "Saved....";
    }

//    // add planting medium
//    @PutMapping(value = "/update/{mediumId}")
//    public String updateMedium(@PathVariable(value = "mediumId") Integer mediumId, @RequestBody PlantingMedium medium) {
//        PlantingMedium updatedMedium = mediumRepo.findById(mediumId).get();
//        updatedMedium.setMediumType(medium.getMediumType());
//        updatedMedium.setQty(medium.getQty());
//        mediumRepo.save(updatedMedium);
//        return "updated....";
//    }
//
    @DeleteMapping(value = "/delete/{mediumId}")
    public String deleteMedium(@PathVariable Integer mediumId) {
        PlantingMedium deletedMedium = mediumRepo.findById(mediumId).get();
        mediumRepo.delete(deletedMedium);
        return "deleted...";
    }


}




package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.PlantingMediumRepository;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
import ingis.microgreenappapi.models.PlantingMedium;
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
    }

// add planting medium
    @PostMapping(value = "/add")
    public String addMedium(@RequestBody PlantingMedium medium) {
        mediumRepo.save(medium);
        return "Saved....";
    }

// update planting medium
    @PutMapping(value = "/update/{mediumId}")
    public String updateMedium(@PathVariable(value = "mediumId") Integer mediumId,
                               @RequestBody PlantingMedium medium) {
        PlantingMedium updatedMedium = mediumRepo.findById(mediumId)
                .orElseThrow(()-> new ResourceNotFoundException("A medium does not exist with id:" + mediumId));
        updatedMedium.setMediumType(medium.getMediumType());
        updatedMedium.setQty(medium.getQty());
        mediumRepo.save(updatedMedium);
        return "updated....";
    }

// delete planting medium
    @DeleteMapping(value = "/delete/{mediumId}")
    public String deleteMedium(@PathVariable Integer mediumId) {
        PlantingMedium deletedMedium = mediumRepo.findById(mediumId)
                .orElseThrow(()-> new ResourceNotFoundException("A medium does not exist with id:" + mediumId));
        mediumRepo.delete(deletedMedium);
        return "deleted...";
    }

}




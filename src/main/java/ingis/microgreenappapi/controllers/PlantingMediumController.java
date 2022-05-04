package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.PlantingMediumRepository;
import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.PlantingMedium;
import ingis.microgreenappapi.models.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlantingMediumController {

    @Autowired
    private PlantingMediumRepository mediumRepo;

    // view all planting medium information

    @GetMapping("/medium")
    public List<PlantingMedium> getMedium() {
        return mediumRepo.findAll();
    }
}

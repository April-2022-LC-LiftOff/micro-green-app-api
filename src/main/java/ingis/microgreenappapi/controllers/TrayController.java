package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.data.TrayRepository;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.Tray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrayController {

    @Autowired
    private TrayRepository trayRepo;

    // view all tray information

    @GetMapping("/trays")
    public List<Tray> getTrays() {
        return trayRepo.findAll();
    }

}

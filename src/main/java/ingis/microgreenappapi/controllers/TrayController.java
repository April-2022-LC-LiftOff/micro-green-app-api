package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.TrayRepository;
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

    @PutMapping(value = "/update/{trayId}")
    public String updateTray(@PathVariable(value = "trayId") Integer trayId, @RequestBody Tray tray) {
        Tray updatedTray = trayRepo.findById(trayId).get();
        updatedTray.setTrayType(tray.getTrayType());
        updatedTray.setSize(tray.getSize());
        updatedTray.setQty(tray.getQty());
        trayRepo.save(updatedTray);
        return "updated....";
    }

    @DeleteMapping(value = "/delete/{trayId}")
    public String deleteTray(@PathVariable Integer trayId) {
        Tray deletedTray = trayRepo.findById(trayId).get();
        trayRepo.delete(deletedTray);
        return "deleted...";
    }




}

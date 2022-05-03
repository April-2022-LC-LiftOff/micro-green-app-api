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

@Autowired
private SeedRepository seedRepo;

// view all seed information
    @GetMapping("/seeds")
    public List<Seed> getSeeds() {
        return seedRepo.findAll();
    }

// Save seed info
    @PostMapping(value = "/save")
    public String saveSeed(Seed seed) {
        seedRepo.save(seed);
        return "Saved....";
    }

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

//Delete Seed
    @DeleteMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        Seed deleteSeed = seedRepo.findById(id).get();
        seedRepo.delete(deleteSeed);
        return "Deleted......";
    }

   //**** following this was to make sure info was entering ********


//    @GetMapping
//    public String displayAllSeeds(Model model) {
//        model.addAttribute("title", "All Seeds");
//        model.addAttribute("seeds", SeedRepository.getAll());
//        //GET SeedData
//        System.out.println(SeedRepository.getAll());
//        return "seeds/index";
//    }

//    @GetMapping("create")
//    public String displayCreateSeedForm(Model model) {
//        model.addAttribute("title", "Create Seed");
//        return "seeds/create";
//    }
//
//    @PostMapping("create")
//    public String processCreateSeedForm(@ModelAttribute @Valid Seed newSeed,
//                                        Errors errors, Model model) {
//        if(errors.hasErrors()) {
//            model.addAttribute("title", "Create Seeds");
//            model.addAttribute("errorMsg", "Bad data!");
//            return "seeds/create";
//        }
//        return "redirect:";
//    }
//
//
//    @GetMapping("delete")
//    public String displayDeleteEventForm(Model model) {
////        model.addAttribute("title", "Delete Seeds");
////        model.addAttribute("seeds", SeedRepository.getAll());
//
//        return "seeds/delete";
//    }
//
//
//    @PostMapping("delete")
//    public String processDeleteSeedsForm(@RequestParam(required = false) int[] seedIds) {
//
//        if (seedIds != null) {
//            for (int id : seedIds) {
////               SeedRepository.remove(id);
//            }
//        }
//
//        return "redirect:";
//    }
}

package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedData;
import ingis.microgreenappapi.models.Seed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("seeds")
public class SeedController {

//    private static List<Seed> seeds = new ArrayList<>();

// veiw all seed information
    @GetMapping
    public String displayAllSeeds(Model model) {
        model.addAttribute("title", "All Seeds");
        model.addAttribute("seeds", SeedData.getAll());
        //GET SeedData
        System.out.println(SeedData.getAll());
//        return "SeedData.getAll()"
        return "seeds/index";
    }

    @GetMapping("create")
    public String displayCreateSeedForm(Model model) {
        model.addAttribute("title", "Create Seed");
        return "seeds/create";
    }
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String seedName,
//                                         @RequestParam Number seedingDensity,
//                                         @RequestParam Boolean seedPresoak,
//                                         @RequestParam Number blackoutTime,
//                                         @RequestParam Number harvestTime,
//                                         @RequestParam Number qty) {
//        SeedData.add(new Seed(seedName, seedingDensity, seedPresoak,  blackoutTime, harvestTime, qty));
//
//        return "redirect:";
//    }

    @PostMapping("create")
    public String processCreateSeedForm(@ModelAttribute Seed newSeed) {
        SeedData.add(newSeed);

        return "redirect:";
    }


    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Seeds");
        model.addAttribute("seeds", SeedData.getAll());

        return "seeds/delete";
    }


    @PostMapping("delete")
    public String processDeleteSeedsForm(@RequestParam(required = false) int[] seedIds) {

        if (seedIds != null) {
            for (int id : seedIds) {
               SeedData.remove(id);
            }
        }

        return "redirect:";
    }


//    @GetMapping("seeds")
//    @ResponseBody
//    public String seeds() {
//        return "seeds";
//    }
//
//    @GetMapping("inventory/seeds")
//    @ResponseBody
//    public String seedsInInventory() {
//
//        return "seeds in inventory";
//    }



}

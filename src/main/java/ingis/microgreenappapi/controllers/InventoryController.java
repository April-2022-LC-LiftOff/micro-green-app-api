package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//
@RestController
//@RequestMapping
public class InventoryController {

    @Autowired
    private SeedRepository seedRepo;

    // view all seed information

    @GetMapping("/inventory")
    public List<Seed> viewInventory() {
        return seedRepo.findAll();
    }

    @PostMapping(value = "/add")
    public String addSeedsToInventory(@RequestBody Seed seed) {
        seedRepo.save(seed);
        return "Saved....";
    }

//    public List<Seed> getSeeds() {
//        return seedRepo.findAll();
//    }

//    @GetMapping("/inventory")
//    public ArrayList<Object> getInventory() {
//    public String getInventory() {
//
//        ArrayList<Object> currentInventory = new ArrayList<>();
////        // Adding elements to the Map
////        // using standard put() method
////        Seed updatedSeed = seedRepo.findById(1).get();
////        updatedSeed.setSeedName(seed.getSeedName());
//
//
////        System.out.println(name);
//        String name = seedRepo.findById(15).get().getSeedName();
//        Integer qty = seedRepo.findById(15).get().getQty();
//        Object obj = name,
//        currentInventory.put(name,qty,);
//        currentInventory.put("8x6 base", 30);
//        currentInventory.put("promix", 20);
////
//        return currentInventory;
//        return "found";



//

//        return map;
//    }
//   HashMap<String, Integer> currentInventory = new HashMap<>();

//        HashMap<String, Integer> map = new HashMap<>();
//
//        // Adding elements to the Map
//        // using standard put() method
//        map.put("broccoli", 10);
//        map.put("8x6 base", 30);
//        map.put("promix", 20);
//
//        // Print size and content of the Map
//        System.out.println("Size of map is:- "
//                + map.size());
//
//        // Printing elements in object of Map
//        System.out.println(map);




}


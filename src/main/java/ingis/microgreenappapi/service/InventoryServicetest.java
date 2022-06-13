package ingis.microgreenappapi.service;

import ingis.microgreenappapi.data.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServicetest {

    @Autowired
    private static SeedRepository seedRepo;


    public static void checkInventory(int seedId, int qty) {
        System.out.println("Seed " + seedId);
//        int currentSeedQty = seedRepo.findById(seedId).get().getQty();
//        int orderedSeedQty = qty * seedRepo.findById(seedId).get().getSeedingDensity();
////        if (currentSeedQty > orderedSeedQty) {
////            return true;
////        } else {
////            return false;
////        }
    }

//    public static void updateInventorySeedQty(int seedId, int qty) {
//        System.out.println(seedId + "qty " + qty);
////        Seed updatedSeed = seedRepo.findById(2).
////        updatedSeed.setQty(seedRepo.findById(seedId).get().getQty() -
////                (qty * seedRepo.findById(seedId).get().getSeedingDensity()));
////        seedRepo.save(updatedSeed);
//    }
}

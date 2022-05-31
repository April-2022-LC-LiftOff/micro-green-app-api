package ingis.microgreenappapi.controllers;


import ingis.microgreenappapi.data.OrderDetailsRepository;
import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.data.TaskRepository;
import ingis.microgreenappapi.models.OrderDetails;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/details")
public class OrderDetailsController {

    // ***** Autowired Repositories
    @Autowired
    private OrderDetailsRepository detailRepo;

    @Autowired
    private SeedRepository seedRepo;

    @Autowired
    private TaskRepository taskRepo;

// ******

    @GetMapping
    public List<OrderDetails> getOrderDetails() {
        return  detailRepo.findAll();
    }

    @PostMapping(value = "/add")
    public String addOrderDetail(@RequestBody OrderDetails orderDetail) {

        Seed seed = seedRepo.findById(orderDetail.getSeedRefId()).get();
        // Check to see if enough inventory
        if (seed.getQty() - orderDetail.getQty() < 0) {
            return "Not enough inventory for order";
        }
        // adjust seed inventory
        seed.setQty(seed.getQty() - (orderDetail.getQty() * seed.getSeedingDensity()));

        //Create tasks
        LocalDate dd = LocalDate.parse("2022-05-30");

        String todayTask;
        Task task;

        if (seed.getSeedPresoak()) {
            //Create soak tasks
            todayTask = "Soak " + seed.getSeedName();
            task = new Task();
            task.setTask(todayTask);
            task.setDueDate(dd.minusDays(seed.getHarvestTime()+1));
            taskRepo.save(task);
        }

        //Create water above days
        for (int i = 1; i < seed.getBlackoutTime(); i++) {
            if (i == 1) {
                //Plant & water from above
                todayTask = "Plant " + seed.getSeedName() + ", cover & move to dark racks";
                task = new Task();
                task.setTask(todayTask);
                task.setDueDate(dd.minusDays(seed.getHarvestTime()));
                taskRepo.save(task);
            }
            //Just water from above
            todayTask = "Water " + seed.getSeedName() + " from above";
            task = new Task();
            task.setTask(todayTask);
            task.setDueDate(dd.minusDays(seed.getHarvestTime()-i));
            taskRepo.save(task);
        }

        // Create water below days
        for (int i = (seed.getHarvestTime()- seed.getBlackoutTime()); i > 0; i--) {
            if (i == (seed.getHarvestTime()- seed.getBlackoutTime())) {
                todayTask = "Move " + seed.getSeedName() + " lighted racks and water below";
                task = new Task();
                task.setTask(todayTask);
                task.setDueDate(dd.minusDays(i));
                taskRepo.save(task);
            } else {
                todayTask = "Water " + seed.getSeedName() + " from below";
                task = new Task();
                task.setTask(todayTask);
                task.setDueDate((dd.minusDays(i)));
                taskRepo.save(task);
            }
        }

        //Create pull for delivery
        todayTask = "Pull " + seed.getSeedName() + " for delivery";
        task = new Task();
        task.setTask(todayTask);
        task.setDueDate(dd);
        taskRepo.save(task);

        seedRepo.save(seed);
        detailRepo.save(orderDetail);
//        return newQty.toString();
        return " processed";
    }

    @PostMapping(value = "/add/{deliveryDate}")
    public String addOrderDetail(@PathVariable String deliveryDate, @RequestBody OrderDetails orderDetail) {

        Seed seed = seedRepo.findById(orderDetail.getSeedRefId()).get();
        // Check to see if enough inventory
        if (seed.getQty() - orderDetail.getQty() < 0) {
            return "Not enough inventory for order";
        }
        // adjust seed inventory
        seed.setQty(seed.getQty() - (orderDetail.getQty() * seed.getSeedingDensity()));

        //Create tasks
        LocalDate dd = LocalDate.parse(deliveryDate);

//        String todayTask;
//        Task task;
//
//        if (seed.getSeedPresoak()) {
//            //Create soak tasks
//            todayTask = "Soak " + seed.getSeedName();
//            task = new Task();
//            task.setTask(todayTask);
//            task.setDueDate(dd.minusDays(seed.getHarvestTime()+1));
//            taskRepo.save(task);
//        }
//
//        //Create water above days
//        for (int i = 1; i < seed.getBlackoutTime(); i++) {
//            if (i == 1) {
//                //Plant & water from above
//                todayTask = "Plant " + seed.getSeedName() + ", cover & move to dark racks";
//                task = new Task();
//                task.setTask(todayTask);
//                task.setDueDate(dd.minusDays(seed.getHarvestTime()));
//                taskRepo.save(task);
//            }
//            //Just water from above
//            todayTask = "Water " + seed.getSeedName() + " from above";
//            task = new Task();
//            task.setTask(todayTask);
//            task.setDueDate(dd.minusDays(seed.getHarvestTime()-i));
//            taskRepo.save(task);
//        }
//
//        // Create water below days
//        for (int i = (seed.getHarvestTime()- seed.getBlackoutTime()); i > 0; i--) {
//            if (i == (seed.getHarvestTime()- seed.getBlackoutTime())) {
//                todayTask = "Move " + seed.getSeedName() + " lighted racks and water below";
//                task = new Task();
//                task.setTask(todayTask);
//                task.setDueDate(dd.minusDays(i));
//                taskRepo.save(task);
//            } else {
//                todayTask = "Water " + seed.getSeedName() + " from below";
//                task = new Task();
//                task.setTask(todayTask);
//                task.setDueDate((dd.minusDays(i)));
//                taskRepo.save(task);
//            }
//        }
//
//        //Create pull for delivery
//        todayTask = "Pull " + seed.getSeedName() + " for delivery";
//        task = new Task();
//        task.setTask(todayTask);
//        task.setDueDate(dd);
//        taskRepo.save(task);

        seedRepo.save(seed);
        detailRepo.save(orderDetail);
//        return newQty.toString();
        return " processed";
    }
}

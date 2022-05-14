package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.TaskRepository;
import ingis.microgreenappapi.data.TrayRepository;
import ingis.microgreenappapi.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskCalendar {

    @Autowired
    private TaskRepository taskRepo;

//    @Autowired
//    private TrayRepository trayRepo;

//    LocalDate today = LocalDate.now();
//    LocalDate newLocalDate = today.minusDays(10);

    @GetMapping
    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    @PostMapping
    public String addTasks( @Valid @NotNull @RequestBody Task tasks) {
        taskRepo.save(tasks);
        return "Saved....";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable Integer taskId) {
        Task deletedTask = taskRepo.findById(taskId).get();
        taskRepo.delete(deletedTask);
        return "deleted...";
    }



//HashMap<Integer, Object> todaysTasks = new HashMap<>();
//todaysTasks.put();
////  todaysTasks.put(1,1,"take the dog for a walk");
////            "id": 1,
////            "completed": false,
////            "task": "take the dog for a walk"
//
//    public void startPresoak() {
//        loop through CustomerOrder
//        if CustomerOrder.orderCompleted = false and active = false {
//            loop through orderDetail {
//                if seed.presoak {
//                    if "wheatgrass" {
//                        if today = CustomerOrder.deliverDate.minus(harvest + 2)
//                            set active = true
//                            newTask = "soak: " seedingDensity * traySquareInch seed "for" customerName
//                            add newTask to todayOnTheFarm hashMap
//                    } else{
//                        if today = CustomerOrder.deliverDate.minus(harvest + 1)
//                        set active = true
//                        newTask = "soak: " seedingDensity * traySquareInch seed "for" customerName
//                        add newTask to todayOnTheFarm hashMap
//                    }
//                }
//            }
//        }
//    }
//
//    public void startPlant() {
//        loop through customerOrder {
//            if (CustomerOrder.orderCompleted = false and active = false)
//            or(CustomerOrder.orderCompleted = false and presoak = true) {
//                if today = CustomerOrder.deliverDate.minus(harvest) {
//                    set active = true
//                    loop through OrderDetails
//                            newTask = "Plant" OrderDetail.qty + OrderDetail.trayID
//                    next detail
//                }
//            }
//        }
//    }
//
//    public void water() {
//        loop through customeOrder
//            if active {
//                loop through customerDetail
//                    if (blackoutTime) {
//                        newTask = "Top spray" customerName seed;
//                        if (time to uncover) {
//                            newTask = "Uncover and trasfer " customerName seed "tray light racks"
//                        }
//                    } else {
//                        if (!delivery date = today){
//                            newTask = "Water below: " customerName seed "tray";
//                        }
//                    }
//                next detail
//            }
//        next customerOrder
//    }
//
//
//
//    public List<todaysTasks> todayOnFarm() {
//        return todaysTasks.findAll();
//
//    }



}
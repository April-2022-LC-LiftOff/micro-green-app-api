package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.data.TaskRepository;
import ingis.microgreenappapi.exception.NotEnoughInventoryException;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.Task;
import ingis.microgreenappapi.service.InventoryServicetest;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.data.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class CustomerOrderController {

        @Autowired
        private CustomerOrderRepository customerOrderRepository;

        @Autowired
        private SeedRepository seedRepo;

        @Autowired
        private TaskRepository taskRepo;

        //view all customer orders
        @GetMapping
        public List<CustomerOrder> getAllOrders(){

            return customerOrderRepository.findAll();
        }


        //create order
        @PostMapping("/create")
        public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder) {

            System.out.println( customerOrder.getOrderDetails().size());

            for (int i = 0; i < customerOrder.getOrderDetails().size(); i ++) {

                int seedId = customerOrder.getOrderDetails().get(i).getSeed().getSeedId();
                Seed seed = seedRepo.getById(seedId);
                int seedQtyOnHand =seed.getQty();
                int seedQtyOrdered = customerOrder.getOrderDetails().get(i).getQty() *
                        seed.getSeedingDensity();
                String todayTask;
                Task task;
                LocalDate deliveryDate = customerOrder.getDeliveryDate();

                //check inventory
                if (seedQtyOnHand < seedQtyOrdered) {
                    throw new NotEnoughInventoryException("Not enough inventory for order");
                }

                //Update Inventory
                seed.setQty(seedQtyOnHand - seedQtyOrdered);

                //Create tasks
                if (seed.getSeedPresoak()) {
                    //Create soak tasks
                    todayTask = "Soak " + seed.getSeedName();
                    task = new Task();
                    task.setTask(todayTask);
                    task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime()+1)));
                    taskRepo.save(task);
                }

                //Create water above days
                for (i = 1; i < seed.getBlackoutTime(); i++) {
                    if (i == 1) {
                        //Plant & water from above
                        todayTask = "Plant " + seed.getSeedName() + ", cover & move to dark racks";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime())));
                        taskRepo.save(task);
                    }
                }
//                    //Just water from above
//                    todayTask = "Water " + seed.getSeedName() + " from above";
//                    task = new Task();
//                    task.setTask(todayTask);
//                    task.setDueDate(dd.minusDays(seed.getHarvestTime()-i));
//                    taskRepo.save(task);
//                }

//                // Create water below days
//                for (int i = (seed.getHarvestTime()- seed.getBlackoutTime()); i > 0; i--) {
//                    if (i == (seed.getHarvestTime()- seed.getBlackoutTime())) {
//                        todayTask = "Move " + seed.getSeedName() + " lighted racks and water below";
//                        task = new Task();
//                        task.setTask(todayTask);
//                        task.setDueDate(dd.minusDays(i));
//                        taskRepo.save(task);
//                    } else {
//                        todayTask = "Water " + seed.getSeedName() + " from below";
//                        task = new Task();
//                        task.setTask(todayTask);
//                        task.setDueDate((dd.minusDays(i)));
//                        taskRepo.save(task);
//                    }
//                }

//                //Create pull for delivery
//                todayTask = "Pull " + seed.getSeedName() + " for delivery";
//                task = new Task();
//                task.setTask(todayTask);
//                task.setDueDate(dd);
//                taskRepo.save(task);
//
//                seedRepo.save(seed);
//                detailRepo.save(orderDetail);
//        return newQty.toString();
//                return " processed";

            }

            return customerOrderRepository.save(customerOrder);
        }



            ;

  //get order by Id
        @GetMapping("/{orderId}")
        public ResponseEntity<CustomerOrder>getOrderById(@PathVariable int orderId){
            CustomerOrder customerOrder = customerOrderRepository.findById(orderId)
                    .orElseThrow(()->new ResourceNotFoundException("Customer order does not exist with id:" + orderId));
        return ResponseEntity.ok(customerOrder);
        }

        //update order by Id
        @PutMapping("/update/{orderId}")
    public ResponseEntity<CustomerOrder>updateOrder(@PathVariable int orderId, CustomerOrder orderDetails){
            CustomerOrder updateOrder = customerOrderRepository.findById(orderId)
                    .orElseThrow(()-> new ResourceNotFoundException("Customer order does not exist with id:" + orderId));
                updateOrder.setOrderDate(orderDetails.getOrderDate());
                updateOrder.setDeliveryDate(orderDetails.getDeliveryDate());
                updateOrder.setActiveOrder(orderDetails.getActiveOrder());
//                updateOrder.setSeed(orderDetails.getSeed());
//                updateOrder.setTray(orderDetails.getTray());
//                updateOrder.setPlantingMedium(orderDetails.getPlantingMedium());
                updateOrder.setCustomer(orderDetails.getCustomer());
                updateOrder.setOrderDetails(orderDetails.getOrderDetails());

                customerOrderRepository.save(updateOrder);
                return ResponseEntity.ok(updateOrder);
        }

        //delete order by Id
        @DeleteMapping("/delete/{orderId}")
        public ResponseEntity<HttpStatus>deleteOrder(@PathVariable int orderId){
            CustomerOrder customerOrder = customerOrderRepository.findById(orderId)
                    .orElseThrow((()->new ResourceNotFoundException("Customer order does not exist with id:" + orderId)));
                    customerOrderRepository.delete(customerOrder);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


//    public void checkInventory(int seedId, int qty) {
//        System.out.println("Seed " + seedId);
//        int currentSeedQty = seedRepo.findById(seedId).get().getQty();
//    //        int orderedSeedQty = qty * seedRepo.findById(seedId).get().getSeedingDensity();
//    ////        if (currentSeedQty > orderedSeedQty) {
//    ////            return true;
//    ////        } else {
//    ////            return false;
//    ////        }
//    }
}








        //add new order
        //enter Customer name
        // enter seed, tray size, qty
            //calculate seed order for tray size
                //if not enough seed, send error message
            //Are there enough trays item
                // if not, send error message
             // is there enough panting medium for tray size
                // if not, send error message
        //reduce inventory(seed, tray, planting medium)


    //order cancelled before planting
        // new order

    //order delivered
        //add trays back into inventory


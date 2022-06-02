package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.data.TaskRepository;
import ingis.microgreenappapi.exception.NotEnoughInventoryException;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.data.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

            for (int i = 0; i < customerOrder.getOrderDetails().size(); i ++) {

                //intialize variables
                int seedId = customerOrder.getOrderDetails().get(i).getSeed().getSeedId();

                Seed seed = seedRepo.findById(seedId)
                        .orElseThrow(()-> new ResourceNotFoundException("Customer order does not exist with id:"));

                int SeedQtyInInventory = customerOrder.getOrderDetails().get(i).getSeed().getQty();
                int seedQtyOrdered = customerOrder.getOrderDetails().get(i).getQty() *
                        customerOrder.getOrderDetails().get(i).getSeed().getSeedingDensity();
                String todayTask;
                Task task;
                LocalDate deliveryDate = customerOrder.getDeliveryDate();

                //check inventory
                if (SeedQtyInInventory < seedQtyOrdered) {
                    throw new NotEnoughInventoryException("Not enough inventory for order");
                }

                //Update Inventory
                seed.setQty(SeedQtyInInventory - seedQtyOrdered);

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

                    //Just water from above
                    todayTask = "Water " + seed.getSeedName() + " from above";
                    task = new Task();
                    task.setTask(todayTask);
                    task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime()-i)));
                    taskRepo.save(task);
                }

                // Create water below days
                for (i = (seed.getHarvestTime()- seed.getBlackoutTime()); i > 0; i--) {
                    if (i == (seed.getHarvestTime()- seed.getBlackoutTime())) {
                        todayTask = "Move " + seed.getSeedName() + " lighted racks and water below";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf(deliveryDate.minusDays(i)));
                        taskRepo.save(task);
                    } else {
                        todayTask = "Water " + seed.getSeedName() + " from below";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf((deliveryDate.minusDays(i))));
                        taskRepo.save(task);
                    }
                }

                //Create pull for delivery
                todayTask = "Pull " + seed.getSeedName() + " for delivery";
                task = new Task();
                task.setTask(todayTask);
                task.setDueDate(String.valueOf(deliveryDate));
                taskRepo.save(task);

            }

            return customerOrderRepository.save(customerOrder);
        }


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


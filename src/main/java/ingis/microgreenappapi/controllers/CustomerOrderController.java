package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.*;
import ingis.microgreenappapi.exception.NotEnoughInventoryException;
import ingis.microgreenappapi.models.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.web.bind.annotation.CrossOrigin;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
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

        @Autowired
        private CustomerRepository customerRepo;

        @Autowired
        private TrayRepository trayRepo;

        //view all customer orders
        @GetMapping
        public List<CustomerOrder> getAllOrders(){
            return customerOrderRepository.findAll();
        }

        //create order
        @PostMapping("/create")
        public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder) {


            //customer exception handling
            int customerId = customerOrder.getCustomer().getCustomerId();
            Customer customer = customerRepo.findById(customerId)
                    .orElseThrow(()-> new ResourceNotFoundException(
                            "Customer not exist with id:" + customerId));

            for (int i = 0; i < customerOrder.getOrderDetails().size(); i ++) {

                //exception handling
                int seedId = customerOrder.getOrderDetails().get(i).getSeed().getSeedId();
                Seed seed = seedRepo.findById(seedId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Seed does not exist with id:" + seedId));

                int trayId = customerOrder.getOrderDetails().get(i).getTray().getTrayId();
                Tray tray = trayRepo.findById(trayId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Tray does not exist with id:" + trayId));

                //initialize variables
                float sqIn = 0;
                if (tray.getSize().contains("10x20")) {
                     sqIn = 200;
                } else if (tray.getSize().contains("5x8")) {
                     sqIn = 40;
                }
                float seedQtyOrdered = customerOrder.getOrderDetails().get(i).getQty() *
                        customerOrder.getOrderDetails().get(i).getSeed().getSeedingDensity() * sqIn;
                float SeedQtyInInventory = customerOrder.getOrderDetails().get(i).getSeed().getQty();
                String customerName = customerOrder.getCustomer().getCustomerName();
                String todayTask;
                Task task;
                LocalDate deliveryDate = customerOrder.getDeliveryDate();

                //check inventory
                if (SeedQtyInInventory < seedQtyOrdered) {
                    throw new NotEnoughInventoryException("Not enough " + seed.getSeedName() +
                            " in inventory for order.");
                }

                if (tray.getQty() < customerOrder.getOrderDetails().get(i).getQty()) {
                    throw new NotEnoughInventoryException("Not enough " + tray.getTrayType() +
                            " on hand for order.");
                }

                //Update Inventory
                seed.setQty(SeedQtyInInventory - seedQtyOrdered);
                tray.setQty(tray.getQty() - customerOrder.getOrderDetails().get(i).getQty());

                //Create tasks
                if (seed.getSeedPresoak()) {
                    //Create soak tasks
                    todayTask = "Order for " + customerName +
                            "\nSoak "  + (int)customerOrder.getOrderDetails().get(i).getQty() +
                            " " + tray.getSize() +
                            " tray(s) of " +
                            seed.getSeedName();
                    task = new Task();
                    task.setTask(todayTask);
                    task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime()+1)));
                    taskRepo.save(task);
                }

                //Create water above days
                for (int j = 1; j < seed.getBlackoutTime(); j++) {
                    if (j == 1) {
                        //Plant & water from above
                        todayTask = "Order for " + customerName + "\nPlant " +  (int)customerOrder.getOrderDetails().get(i).getQty() +  " " +
                                tray.getSize() + " tray(s) of " +seed.getSeedName() + ", cover & move to dark racks";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime())));
                        taskRepo.save(task);
                    }

                    //Just water from above
                    todayTask = "Order for " + customerName + "\nWater " + seed.getSeedName() + " from above";
                    task = new Task();
                    task.setTask(todayTask);
                    task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime()-j)));
                    taskRepo.save(task);
                }


                // Create water below days
                for (i = (seed.getHarvestTime()- seed.getBlackoutTime()); i > 0; i--) {
                    if (i == (seed.getHarvestTime()- seed.getBlackoutTime())) {
                        todayTask = "Order for " + customerName + "\nMove " + seed.getSeedName() + " lighted racks and water below";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf(deliveryDate.minusDays(i)));
                        taskRepo.save(task);
                    } else {
                        todayTask = "Order for " + customerName + "\nWater " + seed.getSeedName() + " from below";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf((deliveryDate.minusDays(i))));
                        taskRepo.save(task);
                    }
                }

                //Create pull for delivery
                todayTask = "Order for " + customerName + "\nPull " + seed.getSeedName() + " for delivery";
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


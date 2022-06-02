package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.exception.NotEnoughInventoryException;
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

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class CustomerOrderController {

        @Autowired
        private CustomerOrderRepository customerOrderRepository;

        @Autowired
        private SeedRepository seedRepo;

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

                //check inventory
                int seedId = customerOrder.getOrderDetails().get(i).getSeed().getSeedId();
                int seedQtyOnHand =seedRepo.getById(seedId).getQty();
                int seedQtyOrdered = customerOrder.getOrderDetails().get(i).getQty() *
                        seedRepo.getById(seedId).getSeedingDensity();

                if (seedQtyOnHand < seedQtyOrdered) {
                    throw new NotEnoughInventoryException("Not enough inventory for order");
                }

            }

            return customerOrderRepository.save(customerOrder);
        }

//                checkInventory(orderDetails.getSeed().getSeedId(), orderDetails.getQty());
//                CustomerOrderController.checkInventory(orderDetails.getSeed().getSeedId(), orderDetails.getQty());

                //Update Inventory
//                InventoryController.updateInventorySeedQty(orderDetails.getSeed().getSeedId(), orderDetails.getQty());
//                InventoryService.updateInventorySeedQty(orderDetails.getSeed().getSeedId(), orderDetails.getQty());

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


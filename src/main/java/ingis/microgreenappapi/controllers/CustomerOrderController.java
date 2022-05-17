package ingis.microgreenappapi.controllers;

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

        //view all customer orders
        @GetMapping
        public List<CustomerOrder> getAllOrders(){
            return customerOrderRepository.findAll();
        }


        //create order
        @PostMapping
        public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder){
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


package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.CustomerRepository;
import ingis.microgreenappapi.data.OrderDetailsRepository;
import ingis.microgreenappapi.models.Customer;
import ingis.microgreenappapi.service.OrderFormService;
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
    private OrderFormService orderFormService;

    @Autowired
    private  CustomerOrderRepository customerOrderRepository;
    @GetMapping()
    public ResponseEntity<List<CustomerOrder>> getAllOrders() {
        List<CustomerOrder> customerOrders = orderFormService.getAllOrders();
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }

//    create order
//        @PostMapping("/create")
//        public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder){
//           return customerOrderRepository.save(customerOrder);
//        }

    @PostMapping("/create")
    public ResponseEntity<CustomerOrder> addOrder(@RequestBody CustomerOrder customerOrder) {
        CustomerOrder order = orderFormService.addOrder(customerOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<CustomerOrder> updateOrder(@RequestBody CustomerOrder customerOrder) {
        CustomerOrder order = orderFormService.editOrder(customerOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(@RequestParam(name = "orderId") Integer orderId) {
        orderFormService.deleteOrder(orderId);
        return new ResponseEntity<>("Customer order with ID :" + orderId + " deleted successfully", HttpStatus.OK);
    }
}

//        @Autowired
//        private CustomerOrderRepository customerOrderRepository;
//
//        @Autowired
//        private CustomerRepository customerRepo;
//
//        @Autowired
//        private OrderDetailsRepository orderDetailsRepository;
//
//
//        //view all customer orders
//        @GetMapping
//        public List<CustomerOrder> getAllOrders(){
//
//            return customerOrderRepository.findAll();
//        }
////        @PostMapping("/customers/{customerId}/create")
////        public ResponseEntity<CustomerOrder> createOrder(@PathVariable Integer customerId,
////                                                         @RequestBody CustomerOrder newOrder){
////            CustomerOrder customerOrder = customerRepo.findById(customerId).map(customer ->{
////                newOrder.setCustomer(customer);
////                return customerOrderRepository.save(newOrder);
////            }).orElseThrow(() -> new ResourceNotFoundException("No customer found with id = " + customerId));
////            return new ResponseEntity<>(customerOrder, HttpStatus.CREATED);
////        }
//
////    @PostMapping("/create")
////    public Customer createOrder(String customerName, @RequestBody CustomerOrder newOrder){
////
////            Customer findCustomer = customerRepo.findByCustomerName(customerName);
////            findCustomer.addOrder(newOrder);
////        System.out.println(findCustomer);
////        return customerRepo.save(findCustomer);
////            }
//
//
//
////        create order
//        @PostMapping("/create")
//        public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder){
//           return customerOrderRepository.save(customerOrder);
//        }
//
//        //get order by Id
//        @GetMapping("/{orderId}")
//        public ResponseEntity<CustomerOrder>getOrderById(@PathVariable int orderId){
//            CustomerOrder customerOrder = customerOrderRepository.findById(orderId)
//                    .orElseThrow(()->new ResourceNotFoundException("Customer order does not exist with id:" + orderId));
//        return ResponseEntity.ok(customerOrder);
//        }
//
//        //update order by Id
//        @PutMapping("/update/{orderId}")
//    public ResponseEntity<CustomerOrder>updateOrder(@PathVariable int orderId, CustomerOrder orderDetails){
//            CustomerOrder updateOrder = customerOrderRepository.findById(orderId)
//                    .orElseThrow(()-> new ResourceNotFoundException("Customer order does not exist with id:" + orderId));
//                updateOrder.setOrderDate(orderDetails.getOrderDate());
//                updateOrder.setDeliveryDate(orderDetails.getDeliveryDate());
//                updateOrder.setActiveOrder(orderDetails.getActiveOrder());
////                updateOrder.setSeed(orderDetails.getSeed());
////                updateOrder.setTray(orderDetails.getTray());
////                updateOrder.setPlantingMedium(orderDetails.getPlantingMedium());
//                updateOrder.setCustomer(orderDetails.getCustomer());
//                updateOrder.setOrderDetails(orderDetails.getOrderDetails());
//
//                customerOrderRepository.save(updateOrder);
//                return ResponseEntity.ok(updateOrder);
//        }
//
//        //delete order by Id
//        @DeleteMapping("/delete/{orderId}")
//    public ResponseEntity<HttpStatus>deleteOrder(@PathVariable int orderId){
//            CustomerOrder customerOrder = customerOrderRepository.findById(orderId)
//                    .orElseThrow((()->new ResourceNotFoundException("Customer order does not exist with id:" + orderId)));
//                    customerOrderRepository.delete(customerOrder);
//                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }










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


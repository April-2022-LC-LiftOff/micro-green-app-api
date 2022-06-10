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





package ingis.microgreenappapi.controllers;


import ingis.microgreenappapi.data.CustomerOrderRepository;
import ingis.microgreenappapi.data.OrderDetailsRepository;
import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.models.OrderDetails;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.Tray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderRepository customerOrderRepo;

    @Autowired
    private OrderDetailsRepository orderDetailsRepo;


    @Autowired
    private SeedRepository seedRepo;


    // **** working from the orderDetails is created

    @GetMapping
    public List<CustomerOrder> getCustomerOrders() {
        return  customerOrderRepo.findAll();
    }


    @PostMapping(value = "/create")
    public String addOrderDetail(@RequestBody CustomerOrder customerOrder) {

        //todo check inventory for enough qty
        //todo deduct from inventory
        //todo add to tasks

        customerOrderRepo.save(customerOrder);
        return "saved......";
}

        //todo deduct from inventory
//
        //todo add to tasks



}

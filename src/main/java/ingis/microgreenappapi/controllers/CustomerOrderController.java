package ingis.microgreenappapi.controllers;


import ingis.microgreenappapi.data.OrderDetailsRepository;
import ingis.microgreenappapi.data.SeedRepository;
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
    private OrderDetailsRepository orderDetailsRepo;

    @Autowired
    private SeedRepository seedRepo;


    // **** working from the orderDetails is created

    @GetMapping
    public List<OrderDetails> getOrderDetails() {
        return orderDetailsRepo.findAll();
    }

    @PostMapping(value = "/add")
    public String addOrderDetail(@RequestBody OrderDetails orderDetails) {
        orderDetailsRepo.save(orderDetails);
        return "saved......";
}

        //todo deduct from inventory
//
        //todo add to tasks



}

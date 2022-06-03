package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.OrderDetailsRepository;
import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.models.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @GetMapping
    public List<OrderDetails> getAllOrderDetails(){
        return orderDetailsRepository.findAll();
    }

    @PostMapping("/create")
    public OrderDetails createOrderDetails(@RequestBody OrderDetails orderDetails){
        return orderDetailsRepository.save(orderDetails);
    }

}

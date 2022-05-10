package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.repositories.CustomerOrderRepo;
import ingis.microgreenappapi.repositories.OrderDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {


    @Autowired
    private CustomerOrderRepo customerOrderRepo;


    //get all orders
    @GetMapping("/view")
    public List<CustomerOrder> getAllCustomerOrders(){
        List<CustomerOrder> customerOrders = new ArrayList<>();
        customerOrderRepo.findAll().forEach(customerOrders::add);
        return customerOrders;
    }

    //get order by id
    @GetMapping("/get/{orderId}")
    public CustomerOrder getCustomerOrder(int orderId){
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElse(null);
        return customerOrder;
    }

    //add new customer order
    @PostMapping("/add")
    public CustomerOrder addCustomerOrder(CustomerOrder customerOrder){
        customerOrderRepo.save(customerOrder);
        return customerOrder;
    }

    //update customer order
    @PutMapping("/update/{orderId}")
    public CustomerOrder updateCustomerOrder(int orderId){
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElse(null);
        customerOrderRepo.save(customerOrder);
        return customerOrder;
    }

    //delete order by id
    @DeleteMapping("/delete/{orderId}")
    public CustomerOrder deleteCustomerOrder(int orderId){
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId).orElse(null);
        customerOrderRepo.deleteById(orderId);
        return customerOrder;
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


package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.CustomerOrderRepository;
import ingis.microgreenappapi.data.CustomerRepository;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
import ingis.microgreenappapi.models.Customer;
import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.models.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {


    @Autowired
    private CustomerRepository customerRepo;

    private CustomerOrderRepository customerOrderRepository;

//    @GetMapping
//    public String customersList(Model model){
//        model.addAttribute("customers", customerRepo.findAll());
//        return "customers";
//    }
//
//    @PostMapping
//    public String addCustomer(@RequestParam String customerName, Model model){
//        Customer newCustomer = new Customer();
//        newCustomer.setCustomerName(customerName);
//        customerRepo.save(newCustomer);
//
//        model.addAttribute("customer", newCustomer);
//        model.addAttribute("customerOrders", customerOrderRepository.findAll());
//        return "redirect:/customer/"+ newCustomer.getCustomerId();
//    }

    // view all seed information

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

@PostMapping("/add")
    public Customer addCustomers(@RequestBody Customer customer) {
           return customerRepo.save(customer);
    }
//@PostMapping("/add")
//    public String addCustomer(Model model){
//        Customer customer = new Customer();
//        customer.setCustomerName("Customer Name");
//
//        CustomerOrder customerOrder = new CustomerOrder();
////        customerOrder.setOrderDate();
////        customerOrder.setDeliveryDate();
////        customerOrder.setOrderDetails().setSeed();
//
//        customer.createOrder(customerOrder);
//
//        customerRepo.save(customer);
//
//        return "customers";
//
//    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId){
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer does not exist with id:" + customerId));
        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/update/{customerId}")
    public String updateCustomer(@PathVariable(value = "customerId") Integer customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerRepo.findById(customerId).get();
        updatedCustomer.setCustomerName(customer.getCustomerName());

        customerRepo.save(updatedCustomer);
        return "updated....";
    }

    @DeleteMapping(value = "/delete/{customerId}")
    public String deleteCustomer(@PathVariable Integer customerId) {
        Customer deletedCustomer = customerRepo.findById(customerId).get();
        customerRepo.delete(deletedCustomer);
        return "deleted...";
    }



}





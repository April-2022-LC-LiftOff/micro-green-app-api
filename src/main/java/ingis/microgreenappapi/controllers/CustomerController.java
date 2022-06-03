package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.CustomerRepository;
import ingis.microgreenappapi.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {


    @Autowired
    private CustomerRepository customerRepo;

    // view all seed information

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    @PostMapping("/add")
    public Customer addCustomers(@RequestBody Customer customer) {
        return customerRepo.save(customer);
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





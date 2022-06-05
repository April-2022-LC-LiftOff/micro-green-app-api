package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.CustomerRepository;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
import ingis.microgreenappapi.models.Customer;
import ingis.microgreenappapi.models.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
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





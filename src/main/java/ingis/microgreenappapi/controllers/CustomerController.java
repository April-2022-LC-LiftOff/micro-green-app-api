package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.CustomerRepository;
import ingis.microgreenappapi.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    // view all customers
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    // add new customer
    @PostMapping(value = "/add")
    public String addCustomers(@Valid @RequestBody Customer customer) {
        customerRepo.save(customer);
        return "/customers";
    }

    // view customer by id
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<Customer>  viewCustomerById(@PathVariable(value = "customerId") Integer customerId) {
        return ResponseEntity.ok( customerRepo.findById(customerId).get());
    }

    // update customer
    @PutMapping(value = "/update/{customerId}")
    public ResponseEntity<Customer>  updateCustomer(@PathVariable(value = "customerId") Integer customerId,
                                                    @RequestBody Customer customer) {
        Customer updatedCustomer = customerRepo.findById(customerId).get();
        updatedCustomer.setCustomerName(customer.getCustomerName());
        return ResponseEntity.ok( customerRepo.save(updatedCustomer));
    }

    // delete customer
    @DeleteMapping(value = "/delete/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {
        Customer deletedCustomer = customerRepo.findById(customerId).get();
        customerRepo.delete(deletedCustomer);
        return ResponseEntity.ok().body("Customer" + customerId + " has been removed");
    }

}





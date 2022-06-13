package ingis.microgreenappapi.controllers;

import ingis.microgreenappapi.data.CustomerRepository;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
import ingis.microgreenappapi.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    public ArrayList<Object> customers = new ArrayList<>();

    // view all customers
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    // view active customers
    @GetMapping(value = "/active")
    public ArrayList viewActiveCustomers() {
        for (int i = 0; i < customerRepo.count(); i++) {
            if(customerRepo.findAll().get(i).isActiveCustomer()) {
                Object customerIsActive = customerRepo.findAll().get(i);
                customers.add(customerIsActive);
            }
        }
        return customers;
    }

    // view active customers
    @GetMapping(value = "/inactive")
    public ArrayList viewNonActiveCustomers() {
        for (int i = 0; i < customerRepo.count(); i++) {
            if (!(customerRepo.findAll().get(i).isActiveCustomer())) {
                Object customerNotActive = customerRepo.findAll().get(i);
                customers.add(customerNotActive);
            }
        }
        return customers;
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
        return ResponseEntity.ok( customerRepo.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer order does not exist with id:" + customerId));
    }

    // update customer
    @PutMapping(value = "/update/{customerId}")
    public ResponseEntity<Customer>  updateCustomer(@PathVariable(value = "customerId") Integer customerId,
                                                    @RequestBody Customer customer) {
        Customer updatedCustomer = customerRepo.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer order does not exist with id:" + customerId));
        updatedCustomer.setCustomerName(customer.getCustomerName());
        return ResponseEntity.ok( customerRepo.save(updatedCustomer));
    }

    // "delete" customer = flag customer to inactive
    @PutMapping(value = "/delete/{customerId}")
    public ResponseEntity<Customer>  deleteCustomer(@PathVariable(value = "customerId") Integer customerId,
                                                    @RequestBody Customer customer) {
        Customer updatedCustomer = customerRepo.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer order does not exist with id:" + customerId));
        updatedCustomer.setActiveCustomer(false);
        return ResponseEntity.ok( customerRepo.save(updatedCustomer));
    }


    // Cannot delete customer because of relationship with orders.
//    @DeleteMapping(value = "/delete/{customerId}")
//    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {
//        Customer deletedCustomer = customerRepo.findById(customerId).get();
//        customerRepo.delete(deletedCustomer);
//        return ResponseEntity.ok().body("Customer" + customerId + " has been removed");
//    }

}





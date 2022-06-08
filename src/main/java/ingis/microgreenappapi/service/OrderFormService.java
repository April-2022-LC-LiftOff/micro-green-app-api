package ingis.microgreenappapi.service;

import ingis.microgreenappapi.data.CustomerOrderRepository;
import ingis.microgreenappapi.data.CustomerRepository;
import ingis.microgreenappapi.data.OrderDetailsRepository;
import ingis.microgreenappapi.data.SeedRepository;
import ingis.microgreenappapi.models.Customer;
import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.models.Seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFormService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

        public List<CustomerOrder> getAllOrders() {
            return customerOrderRepository.findAll();
        }

        public CustomerOrder addOrder(CustomerOrder customerOrder) {
            Customer customer = customerRepository.findByCustomerName(customerOrder.getCustomer().getCustomerName());
            customer.setCustomerName(customerOrder.getCustomer().getCustomerName());
            customerOrder.setCustomer(customer);
            return customerOrderRepository.save(customerOrder);
        }

        public CustomerOrder editOrder(CustomerOrder entity) {
            return customerOrderRepository.save(entity);
        }
        public void deleteOrder(Integer orderId) {
            customerOrderRepository.deleteById(orderId);
        }
    }




package ingis.microgreenappapi.service;

import ingis.microgreenappapi.data.*;
import ingis.microgreenappapi.models.Customer;
import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.models.Seed;
import ingis.microgreenappapi.models.Tray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFormService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private TrayRepository trayRepository;
        public List<CustomerOrder> getAllOrders() {
            return customerOrderRepository.findAll();
        }

        public CustomerOrder addOrder(CustomerOrder customerOrder) {
            Customer customer = customerRepository.findByCustomerName(customerOrder.getCustomer().getCustomerName());
            customer.setCustomerName(customerOrder.getCustomer().getCustomerName());
            customerOrder.setCustomer(customer);
            for (int i = 0; i < customerOrder.getOrderDetails().size(); i ++) {

                String seedName = customerOrder.getOrderDetails().get(i).getSeed().getSeedName();
                Seed seed = seedRepository.findBySeedName(seedName);
                seed.setSeedName(customerOrder.getOrderDetails().get(i).getSeed().getSeedName());
                customerOrder.getOrderDetails().get(i).setSeed(seed);

                String trayType = customerOrder.getOrderDetails().get(i).getTray().getTrayType();
                Tray tray = trayRepository.findByTrayType(trayType);
                tray.setTrayType(customerOrder.getOrderDetails().get(i).getTray().getTrayType());
                customerOrder.getOrderDetails().get(i).setTray(tray);
            }


            return customerOrderRepository.save(customerOrder);
        }

        public CustomerOrder editOrder(CustomerOrder entity) {
            return customerOrderRepository.save(entity);
        }
        public void deleteOrder(Integer orderId) {
            customerOrderRepository.deleteById(orderId);
        }
    }




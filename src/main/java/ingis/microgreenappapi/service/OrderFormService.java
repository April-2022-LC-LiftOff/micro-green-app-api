package ingis.microgreenappapi.service;

import ingis.microgreenappapi.data.*;
import ingis.microgreenappapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

        public List<CustomerOrder> getAllOrders() {
            return customerOrderRepository.findAll();
        }

        public CustomerOrder  getOrderById(@PathVariable(value = "orderId") Integer orderId) {
        return customerOrderRepository.findById(orderId).get();
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

        public CustomerOrder editOrder(@PathVariable(value = "orderId") Integer orderId, @RequestBody CustomerOrder customerOrder) {
            CustomerOrder updatedOrder = customerOrderRepository.findById(orderId).get();

            updatedOrder.setOrderDate(customerOrder.getOrderDate());
            updatedOrder.setDeliveryDate(customerOrder.getDeliveryDate());
//
//            Customer customer = customerRepository.findByCustomerName(customerOrder.getCustomer().getCustomerName());
//            customer.setCustomerName(customerOrder.getCustomer().getCustomerName());
//            updatedOrder.getCustomer().setCustomerName(customerOrder.getCustomer().getCustomerName());

            for (int i = 0; i < customerOrder.getOrderDetails().size(); i ++) {

                updatedOrder.getOrderDetails().get(i).setQty(customerOrder.getOrderDetails().get(i).getQty());

                String seedName = customerOrder.getOrderDetails().get(i).getSeed().getSeedName();
                Seed seed = seedRepository.findBySeedName(seedName);
                seed.setSeedName(customerOrder.getOrderDetails().get(i).getSeed().getSeedName());
                updatedOrder.getOrderDetails().get(i).setSeed(seed);

                String trayType = customerOrder.getOrderDetails().get(i).getTray().getTrayType();
                Tray tray = trayRepository.findByTrayType(trayType);
                tray.setTrayType(customerOrder.getOrderDetails().get(i).getTray().getTrayType());
                updatedOrder.getOrderDetails().get(i).setTray(tray);
            }

            return customerOrderRepository.save(updatedOrder);
        }
        public void deleteOrder(Integer orderId) {
            customerOrderRepository.deleteById(orderId);
        }
    }




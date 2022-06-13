package ingis.microgreenappapi.service;

import ingis.microgreenappapi.data.*;
import ingis.microgreenappapi.exception.NotEnoughInventoryException;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
import ingis.microgreenappapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
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

    @Autowired
    private TaskRepository taskRepository;

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

                String size = customerOrder.getOrderDetails().get(i).getTray().getSize();
                Tray tray = trayRepository.findBySize(size);
                tray.setSize(customerOrder.getOrderDetails().get(i).getTray().getSize());
                customerOrder.getOrderDetails().get(i).setTray(tray);
            }

            //customer exception handling
//            int customerId = customerOrder.getCustomer().getCustomerId();
//            Customer customer = customerRepository.findById(customerId)
//                    .orElseThrow(()-> new ResourceNotFoundException(
//                            "Customer not exist with id:" + customerId));

            for (int i = 0; i < customerOrder.getOrderDetails().size(); i ++) {

                //exception handling
                int seedId = customerOrder.getOrderDetails().get(i).getSeed().getSeedId();
                Seed seed = seedRepository.findById(seedId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Seed does not exist with id:" + seedId));

                int trayId = customerOrder.getOrderDetails().get(i).getTray().getTrayId();
                Tray tray = trayRepository.findById(trayId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Tray does not exist with id:" + trayId));

                //initialize variables
                float sqIn = 0;
                if (tray.getSize().contains("10x20")) {
                    sqIn = 200;
                } else if (tray.getSize().contains("5x8")) {
                    sqIn = 40;
                }
                float seedQtyOrdered = customerOrder.getOrderDetails().get(i).getQty() *
                        customerOrder.getOrderDetails().get(i).getSeed().getSeedingDensity() * sqIn;
                float SeedQtyInInventory = customerOrder.getOrderDetails().get(i).getSeed().getQty();
                String customerName = customerOrder.getCustomer().getCustomerName();
                String todayTask;
                Task task;
                LocalDate deliveryDate = customerOrder.getDeliveryDate();

                //check inventory
                if (SeedQtyInInventory < seedQtyOrdered) {
                    throw new NotEnoughInventoryException("Not enough " + seed.getSeedName() +
                            " in inventory for order.");
                }

                if (tray.getQty() < customerOrder.getOrderDetails().get(i).getQty()) {
                    throw new NotEnoughInventoryException("Not enough " + tray.getTrayType() +
                            " on hand for order.");
                }

                //Update Inventory
                seed.setQty(SeedQtyInInventory - seedQtyOrdered);
                tray.setQty(tray.getQty() - customerOrder.getOrderDetails().get(i).getQty());

                //Create tasks
                if (seed.getSeedPresoak()) {
                    //Create soak tasks
                    todayTask = "Order for " + customerName +
                            "\nSoak "  + (int)customerOrder.getOrderDetails().get(i).getQty() +
                            " " + tray.getSize() +
                            " tray(s) of " +
                            seed.getSeedName();
                    task = new Task();
                    task.setTask(todayTask);
                    task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime()+1)));
                    taskRepository.save(task);
                }

                //Create water above days
                for (int j = 1; j < seed.getBlackoutTime(); j++) {
                    if (j == 1) {
                        //Plant & water from above
                        todayTask = "Order for " + customerName + "\nPlant " +  (int)customerOrder.getOrderDetails().get(i).getQty() +  " " +
                                tray.getSize() + " tray(s) of " +seed.getSeedName() + ", cover & move to dark racks";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime())));
                        taskRepository.save(task);
                    }

                    //Just water from above
                    todayTask = "Order for " + customerName + "\nWater " + seed.getSeedName() + " from above";
                    task = new Task();
                    task.setTask(todayTask);
                    task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime()-j)));
                    taskRepository.save(task);
                }


                // Create water below days
                for (i = (seed.getHarvestTime()- seed.getBlackoutTime()); i > 0; i--) {
                    if (i == (seed.getHarvestTime()- seed.getBlackoutTime())) {
                        todayTask = "Order for " + customerName + "\nMove " + seed.getSeedName() + " lighted racks and water below";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf(deliveryDate.minusDays(i)));
                        taskRepository.save(task);
                    } else {
                        todayTask = "Order for " + customerName + "\nWater " + seed.getSeedName() + " from below";
                        task = new Task();
                        task.setTask(todayTask);
                        task.setDueDate(String.valueOf((deliveryDate.minusDays(i))));
                        taskRepository.save(task);
                    }
                }

                //Create pull for delivery
                todayTask = "Order for " + customerName + "\nPull " + seed.getSeedName() + " for delivery";
                task = new Task();
                task.setTask(todayTask);
                task.setDueDate(String.valueOf(deliveryDate));
                taskRepository.save(task);

            }

            return customerOrderRepository.save(customerOrder);
        }

        public CustomerOrder editOrder(@PathVariable(value = "orderId") Integer orderId, @RequestBody CustomerOrder customerOrder) {
            CustomerOrder updatedOrder = customerOrderRepository.findById(orderId).get();

            updatedOrder.setOrderDate(customerOrder.getOrderDate());
            updatedOrder.setDeliveryDate(customerOrder.getDeliveryDate());


            for (int i = 0; i < customerOrder.getOrderDetails().size(); i ++) {

                updatedOrder.getOrderDetails().get(i).setQty(customerOrder.getOrderDetails().get(i).getQty());

                String seedName = customerOrder.getOrderDetails().get(i).getSeed().getSeedName();
                Seed seed = seedRepository.findBySeedName(seedName);
                seed.setSeedName(customerOrder.getOrderDetails().get(i).getSeed().getSeedName());
                updatedOrder.getOrderDetails().get(i).setSeed(seed);

                String size = customerOrder.getOrderDetails().get(i).getTray().getSize();
                Tray tray = trayRepository.findBySize(size);
                tray.setSize(customerOrder.getOrderDetails().get(i).getTray().getSize());
                updatedOrder.getOrderDetails().get(i).setTray(tray);
            }

            return customerOrderRepository.save(updatedOrder);
        }
        public void deleteOrder(Integer orderId) {
            customerOrderRepository.deleteById(orderId);
        }
    }




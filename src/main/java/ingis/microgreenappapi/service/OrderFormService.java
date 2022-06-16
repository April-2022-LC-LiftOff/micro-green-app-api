package ingis.microgreenappapi.service;

import ingis.microgreenappapi.data.*;
import ingis.microgreenappapi.exception.NotEnoughInventoryException;
import ingis.microgreenappapi.exception.ResourceNotFoundException;
import ingis.microgreenappapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderFormService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private SeedRepository seedRepo;

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private TrayRepository trayRepo;


    public List<CustomerOrder> getAllOrders() {
        return customerOrderRepository.findAll();
    }

    public CustomerOrder addOrder(CustomerOrder customerOrder) {

        // find and set customer
        Customer customer = customerRepository.findByCustomerName(customerOrder.getCustomer().getCustomerName());
        customer.setCustomerName(customerOrder.getCustomer().getCustomerName());
        System.out.println(customer);
        customerOrder.setCustomer(customer);


        // process order detail
//        addOrderProcessDetails(customerOrder);

        return customerOrderRepository.save(customerOrder);
    }

    public CustomerOrder addOrderProcessDetails(CustomerOrder customerOrder) {

        for (int i = 0; i < customerOrder.getOrderDetails().size(); i ++) {

            //exception handling
            int seedId = customerOrder.getOrderDetails().get(i).getSeed().getSeedId();
            Seed seed = seedRepo.findById(seedId)
                    .orElseThrow(()-> new ResourceNotFoundException(
                            "Seed does not exist with id:" + seedId));

            int trayId = customerOrder.getOrderDetails().get(i).getTray().getTrayId();
            Tray tray = trayRepo.findById(trayId)
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
                taskRepo.save(task);
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
                    taskRepo.save(task);
                }

                //Just water from above
                todayTask = "Order for " + customerName + "\nWater " + seed.getSeedName() + " from above";
                task = new Task();
                task.setTask(todayTask);
                task.setDueDate(String.valueOf(deliveryDate.minusDays(seed.getHarvestTime()-j)));
                taskRepo.save(task);
            }


            // Create water below days
            for (i = (seed.getHarvestTime()- seed.getBlackoutTime()); i > 0; i--) {
                if (i == (seed.getHarvestTime()- seed.getBlackoutTime())) {
                    todayTask = "Order for " + customerName + "\nMove " + seed.getSeedName() + " lighted racks and water below";
                    task = new Task();
                    task.setTask(todayTask);
                    task.setDueDate(String.valueOf(deliveryDate.minusDays(i)));
                    taskRepo.save(task);
                } else {
                    todayTask = "Order for " + customerName + "\nWater " + seed.getSeedName() + " from below";
                    task = new Task();
                    task.setTask(todayTask);
                    task.setDueDate(String.valueOf((deliveryDate.minusDays(i))));
                    taskRepo.save(task);
                }
            }

            //Create pull for delivery
            todayTask = "Order for " + customerName + "\nPull " + seed.getSeedName() + " for delivery";
            task = new Task();
            task.setTask(todayTask);
            task.setDueDate(String.valueOf(deliveryDate));
            taskRepo.save(task);

        }

        return customerOrder;
    }

    public CustomerOrder editOrder(CustomerOrder entity) {
        return customerOrderRepository.save(entity);
    }

    public void deleteOrder(Integer orderId) {
        customerOrderRepository.deleteById(orderId);
    }
}

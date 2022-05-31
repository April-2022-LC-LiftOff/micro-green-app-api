package ingis.microgreenappapi;

import ingis.microgreenappapi.data.CustomerOrderRepository;
import ingis.microgreenappapi.data.OrderDetailsRepository;

import ingis.microgreenappapi.models.CustomerOrder;
import ingis.microgreenappapi.models.OrderDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import  java.util.List;

@SpringBootTest
public class OneToManyUnidirectionMappingTest {

    @Autowired
    private CustomerOrderRepository customerOrderRepo;

    @Autowired
    private OrderDetailsRepository orderDetailsRepo;

    @Test
    void testSaveCustomerOrder() {

        //create CustomerOrder object
        CustomerOrder customerOrder =  new CustomerOrder();

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setQty(1);
        orderDetails.setSeedRefId(1);

        customerOrder.add(orderDetails);

        OrderDetails orderDetails2 = new OrderDetails();
        orderDetails2.setQty(4);
        orderDetails.setSeedRefId(2);

        customerOrder.add(orderDetails2);

        customerOrder.setActiveOrder(false);
//        customerOrder.setDeliveryDate("2022-06-15");
        customerOrderRepo.save(customerOrder);
    }
}

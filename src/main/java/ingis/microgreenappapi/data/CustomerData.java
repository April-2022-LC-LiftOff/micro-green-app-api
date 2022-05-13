package ingis.microgreenappapi.data;

import ingis.microgreenappapi.models.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerData {
    private static final Map<Integer, Customer> customers = new HashMap<>();

    public static Collection<Customer> getAll() {
        return customers.values();
    }

    public static Customer getById(int id) {
        return customers.get(id);
    }

    public static void add(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public static void remove(int id) {
        customers.remove(id);
    }
}

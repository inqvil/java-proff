package homework;

import java.util.*;

public class CustomerService {
    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    //https://translated.turbopages.org/proxy_u/en-ru.ru.8b6d3c3c-63ceaa24-146e5146-74722d776562/https/www.geeksforgeeks.org/navigablemap-interface-in-java-with-example/
    private final NavigableMap<Customer, String> customerStringNavigableMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Customer customer = customerStringNavigableMap.firstEntry().getKey();
        try {
            customer.getId();
        }catch (NullPointerException nullPointerException){
            return null;
        }
        return new AbstractMap.SimpleEntry<>(new Customer
                (customer.getId(), customer.getName(), customer.getScores()),
                customerStringNavigableMap.get(customer));

    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Customer customer1 = customerStringNavigableMap.higherKey(customer);
        if (customer1 == null) {
            return null;
        }
        return new AbstractMap.SimpleEntry<>(
                new Customer(customer1.getId(), customer1.getName(), customer1.getScores()),
                customerStringNavigableMap.get(customer1));
    }

    public void add(Customer customer, String data) {
        customerStringNavigableMap.put(customer, data);

    }


}

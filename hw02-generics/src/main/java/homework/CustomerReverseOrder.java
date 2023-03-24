package homework;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {
    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final Deque<Customer> customerDeque = new ArrayDeque<>();

    public void add(Customer customer) {
        customerDeque.push(customer);
    }

    public Customer take() {
        return customerDeque.pop(); // это "заглушка, чтобы скомилировать"
    }
}

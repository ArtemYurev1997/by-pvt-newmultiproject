package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.core.FileWorker;
import by.pvt.newmultiproject.core.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository  extends FileWorker {
    public static String FILE = "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbOrder";

    public OrderRepository() {
    }

    public static List<Order> orders = new ArrayList<>();

    public List<Order> getAllOrders() {
        Object object = deserializeObject(FILE);
        List<Order> orderList = new ArrayList<>();
        if (object instanceof List<?>) {
            orderList = (List<Order>) object;
        }
        return orderList;
    }

    public Order add(Order order) {
        orders = getAllOrders();
        orders.add(order);
        serializeObject(orders, FILE);
        return order;
    }

    public Order updateOrder(Long id, Order newOrder) {
        Object object = deserializeObject(FILE);
        if (object instanceof List<?>) {
            orders = (List<Order>) object;
        }
        Order order = getOrderById(id);
        order.setUserId(newOrder.getUserId());
        order.setCost(newOrder.getCost());
        order.setStatus(newOrder.getStatus());
        serializeObject(orders, FILE);
        return order;
    }

    public Order getOrderById(Long id) {
        orders = getAllOrders();
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Order> getOrderByUserId(Long userId) {
        orders = getAllOrders();
        return orders.stream().filter(order -> order.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public void delete(Long id) {
        orders = getAllOrders();
        if (orders.isEmpty()) {
            return;
        }
        Order bucket = getOrderById(id);
        orders.remove(bucket);
        for (Order order1 : orders) {
            if (order1.getId() > id) {
                order1.setId(order1.getId() - 1);
            }
        }
        serializeObject(orders, FILE);
        System.out.println(orders);
    }
}

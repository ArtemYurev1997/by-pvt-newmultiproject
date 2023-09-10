package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.core.domain.Order;

import java.util.List;

public interface OrderRepositoryDB {
    List<Order> getAllOrders();

    Order add(Order order);

    Order updateOrder(Long id, Order newOrder);

    Order getOrderById(Long id);

    void delete(Long id);

    void addOrder(Order order);

    void update(Long id, Order order);

    List<Order> getOrdersByUserId(Long userId);

    Order getOrderByUserId(Long userId);
}

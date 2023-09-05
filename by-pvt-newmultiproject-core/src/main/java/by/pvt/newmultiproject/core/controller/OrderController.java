package by.pvt.newmultiproject.core.controller;

import by.pvt.newmultiproject.api.dto.OrderDto;
import by.pvt.newmultiproject.api.dto.OrderRequest;
import by.pvt.newmultiproject.api.dto.OrderResponse;
import by.pvt.newmultiproject.core.domain.Order;
import by.pvt.newmultiproject.core.mapper.MappingUtils;
import by.pvt.newmultiproject.core.service.OrderService;

import java.util.List;

public class OrderController {
    private final OrderService orderService;
    private final MappingUtils mappingUtils;

    public OrderController(OrderService orderService, MappingUtils mappingUtils) {
        this.orderService = orderService;
        this.mappingUtils = mappingUtils;
    }

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public List<Order> getAllOrders1() {
        return orderService.getAllOrders1();
    }

    public Order add(Order order) {
        return orderService.add(order);
    }

    public void addOrder(OrderRequest orderRequest) {
        orderService.addUser(orderRequest);
    }

    public Order getOrderById(Long id) {
        return orderService.getOrderById(id);
    }

    public void delete(Long id) {
        orderService.delete(id);
    }

    public Order updateOrder(Long id, Order newOrder)  {
        return orderService.updateOrder(id, newOrder);
    }

    public void update(Long id, OrderRequest orderRequest) {
        orderService.update(id, orderRequest);
    }

    public OrderDto getOrderByOrderId(Long orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    public List<OrderResponse> getOrderByUserId(Long userId) {
        return orderService.getOrderByUser(userId);
    }

    public OrderDto deleteProductByOrderFromBasket(Long productId, Long orderId) {
        return orderService.deleteProductByOrder(productId, orderId);
    }
}

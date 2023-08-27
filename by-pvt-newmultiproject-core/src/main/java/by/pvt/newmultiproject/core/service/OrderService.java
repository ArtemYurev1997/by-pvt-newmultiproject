package by.pvt.newmultiproject.core.service;

import by.pvt.newmultiproject.api.dto.OrderDto;
import by.pvt.newmultiproject.api.dto.OrderResponse;
import by.pvt.newmultiproject.api.dto.ProductResponse;
import by.pvt.newmultiproject.api.enums.Status;
import by.pvt.newmultiproject.core.domain.Basket;
import by.pvt.newmultiproject.core.domain.Order;
import by.pvt.newmultiproject.core.domain.Product;
import by.pvt.newmultiproject.core.mapper.MappingUtils;
import by.pvt.newmultiproject.core.repository.BasketRepository;
import by.pvt.newmultiproject.core.repository.OrderRepository;
import by.pvt.newmultiproject.core.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final BasketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MappingUtils mappingUtils;
    private final ProductService productService;

    public OrderService(BasketRepository bucketRepository, ProductRepository productRepository, OrderRepository orderRepository, MappingUtils mappingUtils, ProductService productService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.mappingUtils = mappingUtils;
        this.productService = productService;
    }


    public OrderResponse createOrder(Long sessionId) {
        OrderResponse order = mappingUtils.mapToOrderResponse(orderRepository.getOrderById(sessionId));
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCost(order.getCost());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setStatus(order.getStatus());
        return orderResponse;
    }

    public List<OrderResponse> getOrderByUser(Long userId) {
        List<OrderResponse> orderResponseList = orderRepository.getOrderByUserId(userId).stream().map(mappingUtils::mapToOrderResponse).collect(Collectors.toList());
        return orderResponseList;
    }

    public OrderResponse changeStatus(Long orderId, Status status) {
        OrderResponse orderResponse = mappingUtils.mapToOrderResponse(orderRepository.getOrderById(orderId));
        orderResponse.setStatus(status);
        return orderResponse;
    }

    public OrderDto getOrderByOrderId(Long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        OrderDto orderDto = mappingUtils.mapToOrderDto(order);
        List<Basket> buckets = bucketRepository.getBucketsByOrderId(orderId);
        List<Long> productIds = buckets.stream().map(product -> product.getProductId()).collect(Collectors.toList());
        List<Product> products = productService.getProductsByIds(productIds);
        List<ProductResponse> productResponseList = products.stream().map(product -> mappingUtils.mapToProductDto(product)).collect(Collectors.toList());
        orderDto.setProducts(productResponseList);
        return orderDto;
    }


    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Order add(Order order) {
        return orderRepository.add(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }

    public Order updateOrder(Long id, Order newOrder) {
        return orderRepository.updateOrder(id, newOrder);
    }
}


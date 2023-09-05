package by.pvt.newmultiproject.core.service;

import by.pvt.newmultiproject.api.dto.OrderDto;
import by.pvt.newmultiproject.api.dto.OrderRequest;
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
import by.pvt.newmultiproject.core.repository.impl.OrderRepositoryDBImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final BasketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final OrderRepositoryDBImpl orderRepositoryDB;
    private final OrderRepository orderRepository;
    private final MappingUtils mappingUtils;
    private final ProductService productService;

    public OrderService(BasketRepository bucketRepository, ProductRepository productRepository, OrderRepositoryDBImpl orderRepositoryDB, OrderRepository orderRepository, MappingUtils mappingUtils, ProductService productService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.orderRepositoryDB = orderRepositoryDB;
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
        List<OrderResponse> orderResponseList = orderRepositoryDB.getOrderByUserId(userId).stream().map(mappingUtils::mapToOrderResponse).collect(Collectors.toList());
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

    public OrderDto deleteProductByOrder(Long productId, Long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        OrderDto orderDto = mappingUtils.mapToOrderDto(order);
        List<Basket> baskets = bucketRepository.getBucketsByOrderId(orderId);
        List<Long> productIds = baskets.stream().map(product -> product.getProductId()).collect(Collectors.toList());
        List<Product> products = productService.getProductsByIds(productIds);
//        Product product = products.stream().filter(product1 -> product1.getId().equals(productId)).findFirst().orElse(new Product());
        Product product = productRepository.getProductById(productId);
        ProductResponse productResponse = mappingUtils.mapToProductDto(product);
        List<ProductResponse> productResponseList = products.stream().map(product1 -> mappingUtils.mapToProductDto(product1)).collect(Collectors.toList());
        orderDto.setProducts(productResponseList);

        List<ProductResponse> responseList = orderDto.getProducts();
        List<ProductResponse> newList = responseList == null ? new ArrayList<>() : new ArrayList<>(responseList);
        newList.remove(productResponse);

        orderDto.setProducts(newList);
        return orderDto;
    }




    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public List<Order> getAllOrders1() {
        return orderRepositoryDB.getAllOrders();
    }

    public Order add(Order order) {
        return orderRepository.add(order);
    }

    public void addUser(OrderRequest orderRequest) {
         orderRepositoryDB.addOrder(mappingUtils.mapToOrderEntity(orderRequest));
    }

    public Order getOrderById(Long id) {
        return orderRepositoryDB.getOrderById(id);
    }

    public void delete(Long id) {
        orderRepositoryDB.delete(id);
    }

    public void update(Long id, OrderRequest orderRequest) {
        orderRepositoryDB.update(id, mappingUtils.mapToOrderEntity(orderRequest));
    }

    public Order updateOrder(Long id, Order newOrder) {
        return orderRepository.updateOrder(id, newOrder);
    }
}


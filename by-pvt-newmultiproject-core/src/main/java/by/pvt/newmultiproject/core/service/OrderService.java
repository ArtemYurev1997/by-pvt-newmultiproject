package by.pvt.newmultiproject.core.service;

import by.pvt.newmultiproject.api.dto.*;
import by.pvt.newmultiproject.api.enums.Status;
import by.pvt.newmultiproject.core.domain.Basket;
import by.pvt.newmultiproject.core.domain.Order;
import by.pvt.newmultiproject.core.domain.Product;
import by.pvt.newmultiproject.core.mapper.MappingUtils;
import by.pvt.newmultiproject.core.repository.BasketRepository;
import by.pvt.newmultiproject.core.repository.ClientRepository;
import by.pvt.newmultiproject.core.repository.OrderRepository;
import by.pvt.newmultiproject.core.repository.ProductRepository;
import by.pvt.newmultiproject.core.repository.impl.BasketRepositoryDBImpl;
import by.pvt.newmultiproject.core.repository.impl.ClientRepositoryDBImpl;
import by.pvt.newmultiproject.core.repository.impl.OrderRepositoryDBImpl;
import by.pvt.newmultiproject.core.repository.impl.ProductRepositoryDBImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final BasketRepository bucketRepository;
    private final BasketRepositoryDBImpl basketRepositoryDB;
    private final ProductRepository productRepository;
    private final ProductRepositoryDBImpl productRepositoryDB;
    private final ClientRepository clientRepository;
    private final ClientRepositoryDBImpl clientRepositoryDB;
    private final OrderRepositoryDBImpl orderRepositoryDB;
    private final OrderRepository orderRepository;
    private final MappingUtils mappingUtils;
    private final ProductService productService;

    public OrderService(BasketRepository bucketRepository, BasketRepositoryDBImpl basketRepositoryDB, ProductRepository productRepository, ProductRepositoryDBImpl productRepositoryDB, ClientRepository clientRepository, ClientRepositoryDBImpl clientRepositoryDB, OrderRepositoryDBImpl orderRepositoryDB, OrderRepository orderRepository, MappingUtils mappingUtils, ProductService productService) {
        this.bucketRepository = bucketRepository;
        this.basketRepositoryDB = basketRepositoryDB;
        this.productRepository = productRepository;
        this.productRepositoryDB = productRepositoryDB;
        this.clientRepository = clientRepository;
        this.clientRepositoryDB = clientRepositoryDB;
        this.orderRepositoryDB = orderRepositoryDB;
        this.orderRepository = orderRepository;
        this.mappingUtils = mappingUtils;
        this.productService = productService;
    }


    public OrderResponse createOrder(Long productId, Long sessionId) {
        OrderResponse orderResponse = null;
        ClientResponse clientResponse = mappingUtils.mapToClientDto(clientRepositoryDB.getClientById(sessionId));
        if(clientResponse.getId().equals(sessionId)) {
            ProductResponse productResponse = mappingUtils.mapToProductDto(productRepositoryDB.getProductById(productId));
            orderRepositoryDB.addOrder(mappingUtils.mapToOrderEntityFromResponse(new OrderResponse(sessionId, productResponse.getPrice(), Status.UNFORMED)));
             orderResponse = mappingUtils.mapToOrderResponse(orderRepositoryDB.getOrder());
        }
        return orderResponse;
    }

    public List<OrderResponse> getOrdersByUser(Long userId) {
        List<OrderResponse> orderResponseList = orderRepositoryDB.getOrdersByUserId(userId).stream().map(mappingUtils::mapToOrderResponse).collect(Collectors.toList());
        return orderResponseList;
    }

    public OrderResponse changeStatus(Long orderId, Integer count) {
        OrderResponse orderResponse = mappingUtils.mapToOrderResponse(orderRepositoryDB.getOrderById(orderId));
        if(orderResponse.getCost() * count >= orderResponse.getCost()) {
            orderResponse.setStatus(Status.DONE);
            orderResponse.setCost(orderResponse.getCost() * count);
            orderRepositoryDB.update(orderResponse.getId(), mappingUtils.mapToOrderEntityFromResponse(orderResponse));
            Basket basket = basketRepositoryDB.getBasketByOrderId(orderId);
            basket.setOrderId(basket.getOrderId());
            basket.setProductId(basket.getProductId());
            basket.setCount(count);
            basketRepositoryDB.updateBasket(basket.getId(), basket);
        }
        return orderResponse;
    }


    public OrderDto getOrderByOrderId(Long orderId) {
//        Order order = orderRepository.getOrderById(orderId);
//        OrderDto orderDto = mappingUtils.mapToOrderDto(order);
//        List<Basket> buckets = bucketRepository.getBucketsByOrderId(orderId);
//        List<Long> productIds = buckets.stream().map(product -> product.getProductId()).collect(Collectors.toList());
//        List<Product> products = productService.getProductsByIds(productIds);
//        List<ProductResponse> productResponseList = products.stream().map(product -> mappingUtils.mapToProductDto(product)).collect(Collectors.toList());
//        orderDto.setProducts(productResponseList);
//        return orderDto;


        Order order = orderRepositoryDB.getOrderById(orderId);
        OrderDto orderDto = mappingUtils.mapToOrderDto(order);
        List<Basket> buckets = basketRepositoryDB.getBasketsByOrderId(orderId);
        List<Long> productIds = buckets.stream().map(product -> product.getProductId()).collect(Collectors.toList());
        List<Product> products = productService.getProductsByIds(productIds);
        List<ProductResponse> productResponseList = products.stream().map(product -> mappingUtils.mapToProductDto(product)).collect(Collectors.toList());
        orderDto.setProducts(productResponseList);
        return orderDto;
    }

    public OrderDto deleteProductByOrder(Long productId, Long orderId) {
//        Order order = orderRepository.getOrderById(orderId);
//        OrderDto orderDto = mappingUtils.mapToOrderDto(order);
//        List<Basket> baskets = bucketRepository.getBucketsByOrderId(orderId);
//        List<Long> productIds = baskets.stream().map(product -> product.getProductId()).collect(Collectors.toList());
//        List<Product> products = productService.getProductsByIds(productIds);
//        List<ProductResponse> productResponseList = products.stream().map(product1 -> mappingUtils.mapToProductDto(product1)).collect(Collectors.toList());
//        orderDto.setProducts(productResponseList);
//
//        List<ProductResponse> responseList = orderDto.getProducts();
//        ProductResponse productResponse = responseList.stream().filter(product1 -> product1.getId().equals(productId)).findFirst().orElse(new ProductResponse());
//        responseList.remove(productResponse);
//        for (ProductResponse productResponse1 : responseList) {
//            if (productResponse1.getId() > productId) {
//                productResponse1.setId(productResponse1.getId() - 1);
//            }
//        }
//        orderDto.setProducts(responseList);
//        return orderDto;

        Order order = orderRepositoryDB.getOrderById(orderId);
        OrderDto orderDto = mappingUtils.mapToOrderDto(order);
        List<Basket> baskets = basketRepositoryDB.getBasketsByOrderId(orderId);
        List<Long> productIds = baskets.stream().map(product -> product.getProductId()).collect(Collectors.toList());
        List<Product> products = productService.getProductsByIds(productIds);
        List<ProductResponse> productResponseList = products.stream().map(product1 -> mappingUtils.mapToProductDto(product1)).collect(Collectors.toList());
        orderDto.setProducts(productResponseList);

        List<ProductResponse> responseList = orderDto.getProducts();
        ProductResponse productResponse = responseList.stream().filter(product1 -> product1.getId().equals(productId)).findFirst().orElse(new ProductResponse());
        responseList.remove(productResponse);
        for (ProductResponse productResponse1 : responseList) {
            if (productResponse1.getId() > productId) {
                productResponse1.setId(productResponse1.getId() - 1);
            }
        }
        orderDto.setProducts(responseList);
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

    public void addOrder(OrderRequest orderRequest) {
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

    public OrderResponse getOrderByUserId(Long userId) {
        return mappingUtils.mapToOrderResponse(orderRepositoryDB.getOrderByUserId(userId));
    }

    public OrderResponse getOrder() {
        return mappingUtils.mapToOrderResponse(orderRepositoryDB.getOrder());
    }
}


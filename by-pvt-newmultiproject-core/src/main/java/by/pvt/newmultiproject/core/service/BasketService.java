package by.pvt.newmultiproject.core.service;

import by.pvt.newmultiproject.api.dto.BasketDto;
import by.pvt.newmultiproject.api.dto.OrderResponse;
import by.pvt.newmultiproject.api.dto.ProductResponse;
import by.pvt.newmultiproject.core.domain.Basket;
import by.pvt.newmultiproject.core.mapper.MappingUtils;
import by.pvt.newmultiproject.core.repository.BasketRepository;
import by.pvt.newmultiproject.core.repository.OrderRepository;
import by.pvt.newmultiproject.core.repository.ProductRepository;
import by.pvt.newmultiproject.core.repository.impl.BasketRepositoryDBImpl;
import by.pvt.newmultiproject.core.repository.impl.OrderRepositoryDBImpl;
import by.pvt.newmultiproject.core.repository.impl.ProductRepositoryDBImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasketService {
    private final BasketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final MappingUtils mappingUtils;
    private final ProductRepositoryDBImpl productRepositoryDB;
    private final BasketRepositoryDBImpl basketRepositoryDB;
    private final OrderRepositoryDBImpl orderRepositoryDB;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public BasketService(BasketRepository bucketRepository, ProductRepository productRepository, MappingUtils mappingUtils, ProductRepositoryDBImpl productRepositoryDB, BasketRepositoryDBImpl basketRepositoryDB, OrderRepositoryDBImpl orderRepositoryDB, OrderRepository orderRepository, OrderService orderService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.mappingUtils = mappingUtils;
        this.productRepositoryDB = productRepositoryDB;
        this.basketRepositoryDB = basketRepositoryDB;
        this.orderRepositoryDB = orderRepositoryDB;
        this.orderRepository = orderRepository;

        this.orderService = orderService;
    }

    public Basket add(Basket bucket) {
        return bucketRepository.add(bucket);
    }

    public void addBasket(Basket basket) {
        basketRepositoryDB.add(basket);
    }

    public void updateBasket(Long id, Basket basket) {
        basketRepositoryDB.updateBasket(id, basket);
    }

    public Basket updateBucket(Long id, Basket newBucket) {
        return bucketRepository.updateBucket(id, newBucket);
    }

    public void delete(Long id) {
        bucketRepository.delete(id);
    }

    public void deleteBasket(Long id) {
        basketRepositoryDB.delete(id);
    }

    public Basket getBucketById(Long id, Long orderId) {
        return bucketRepository.getBucketById(id, orderId);
    }

    public List<Long> getProductIdByBucketList(Long orderId) {
        return basketRepositoryDB.getProductIdByBucketList(orderId);
    }

    public List<Basket> getBucketsByOrderId(Long orderId) {
        List<Basket> buckets = bucketRepository.getAllBuckets();
        List<Basket> bucketList = buckets.stream().filter(bucket -> bucket.getOrderId().equals(orderId)).collect(Collectors.toList());
        if(bucketList.isEmpty()) {
            return new ArrayList<>();
        }
        return bucketList;
    }

    public List<Basket> getBasketByOrderId(Long orderId) {
        return basketRepositoryDB.getBasketsByOrderId(orderId);
    }



    public BasketDto addProduct(Long productId, Long sessionId){
        OrderResponse orderResponse = orderService.createOrder(productId, sessionId);
        BasketDto basketDto = new BasketDto();
        basketDto.setId();
        basketDto.setProductId(productId);
        basketDto.setCount(1);
        basketDto.setOrderId(orderResponse.getId());
        basketRepositoryDB.add(mappingUtils.mapToBasket(basketDto));
        return basketDto;
    }

    public BasketDto deleteProduct(Long productId, Long sessionId){
//        Product product = productRepository.getProductById(productId);
//        BasketDto basketDto = mappingUtils.mapToBasketDto(bucketRepository.getBucketById(sessionId));
//        List<ProductResponse> products = baskeDto.getProducts();
//        List<ProductResponse> newList = products == null ? new ArrayList<>() : new ArrayList<>(products);
//        ProductResponse productResponse = mappingUtils.mapToProductDto(product);
//        newList.remove(productResponse);
//        basketDto.setProducts(newList);
//        bucketRepository.save(mappingUtils.mapToBasket(basketDto));
        return null;
    }
}

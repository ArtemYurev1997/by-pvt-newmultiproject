package by.pvt.newmultiproject.core.controller;

import by.pvt.newmultiproject.api.dto.BasketDto;
import by.pvt.newmultiproject.core.domain.Basket;
import by.pvt.newmultiproject.core.mapper.MappingUtils;
import by.pvt.newmultiproject.core.service.BasketService;

import java.util.List;

public class BasketController {
    private final BasketService bucketService;
    private final MappingUtils mappingUtils;

    public BasketController(BasketService bucketService, MappingUtils mappingUtils) {
        this.bucketService = bucketService;
        this.mappingUtils = mappingUtils;
    }

    public Basket add(Basket bucket) {
        return bucketService.add(bucket);
    }

    public Basket updateBucket(Long id, Basket newBucket) {
        return bucketService.updateBucket(id, newBucket);
    }

    public void delete(Long id) {
        bucketService.delete(id);
    }

    public Basket getBucketById(Long id, Long orderId) {
        return bucketService.getBucketById(id, orderId);
    }

    public List<Basket> getBucketsByOrderId(Long orderId) {
        return bucketService.getBucketsByOrderId(orderId);
    }

    public BasketDto addProduct(Long productId, Long sessionId){
        return bucketService.addProduct(productId, sessionId);
    }
    public BasketDto deleteProduct(Long productId, Long sessionId){
        return bucketService.deleteProduct(productId, sessionId);
    }
}

package by.pvt.newmultiproject.core.service;

import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.dto.ProductResponse;
import by.pvt.newmultiproject.core.domain.Basket;
import by.pvt.newmultiproject.core.domain.Product;
import by.pvt.newmultiproject.core.repository.BasketRepository;
import by.pvt.newmultiproject.core.repository.ProductRepository;
import by.pvt.newmultiproject.core.mapper.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductRepository productRepository;
    private final MappingUtils mappingUtils;
    private final BasketRepository basketRepository;

    public ProductService(ProductRepository productRepository, MappingUtils mappingUtils, BasketRepository basketRepository) {
        this.productRepository = productRepository;
        this.mappingUtils = mappingUtils;
        this.basketRepository = basketRepository;
    }

    public void add(ProductRequest productRequest) {
        Product product = new Product(productRequest.getName(), productRequest.getType(), productRequest.getCode(), productRequest.getPrice());
        List<Product> products = productRepository.getAllProducts();
        product.setId((products.get(products.size() - 1).getId() + 1));
        productRepository.addProduct(product);
    }


    public List<ProductResponse> findAll() {
        return productRepository.getAllProducts().stream().map(mappingUtils::mapToProductDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        productRepository.deleteProduct(id);
    }

    public ProductResponse findById(Long id) {
        return mappingUtils.mapToProductDto(productRepository.getProductById(id));
    }

    public List<ProductResponse> update(Long id, ProductRequest productRequest) {
        return productRepository.updateProducts(id, productRequest).stream().map(mappingUtils::mapToProductDto).collect(Collectors.toList());
    }

//    public ProductResponse getProductByProductId(Long id, Long orderId) {
//
//        return mappingUtils.mapToProductDto(productRepository.getProductByProductId(id, orderId));
//    }

    public ProductResponse getProductByProductId(Long id, Long orderId) {
        List<Product> products = productRepository.getAllProducts();
        Basket basket = basketRepository.getBucketById(id, orderId);
        Product product= products.stream().filter(product1 -> product1.getId().equals(basket.getProductId())).findFirst().orElse(null);
        return mappingUtils.mapToProductDto(product);
    }

    public List<Product> getProductsByIds(List<Long> ids) {
        List<Product> productList = ids.stream().map(productRepository::getProductById).collect(Collectors.toList());
        return productList;
    }

    public List<ProductResponse> getProductsByOrderId(Long orderId) {
        List<Long> productIdByBucketList = basketRepository.getProductIdByBucketList(orderId);
        List<Product> productList = productIdByBucketList.stream().map(productRepository::getProductById).collect(Collectors.toList());
        List<ProductResponse> productResponseList = productList.stream().map(mappingUtils::mapToProductDto).collect(Collectors.toList());
        return productResponseList;
    }
}

package by.pvt.newmultiproject.core.controller;

import by.pvt.newmultiproject.api.contract.ProductApi;
import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.dto.ProductResponse;
import by.pvt.newmultiproject.core.domain.Product;
import by.pvt.newmultiproject.core.service.ProductService;

import java.util.List;

public class ProductController implements ProductApi {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productService.findAll();
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        productService.add(productRequest);
    }

    @Override
    public void deleteProduct(Long id) {
        productService.delete(id);
    }

    @Override
    public ProductResponse findProductById(Long id) {
        return productService.findById(id);
    }

    @Override
    public List<ProductResponse> update(Long id, ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }

    public void updateDB(Long id, ProductRequest productRequest) {
        productService.updateDB(id, productRequest);
    }

    public ProductResponse getProductByProductId(Long id, Long orderId) {
        return productService.getProductByProductId(id, orderId);
    }

    public List<Product> getProductsByIds(List<Long> ids) {
        return productService.getProductsByIds(ids);
    }

    public List<ProductResponse> getProductsByOrderId(Long orderId) {
        return productService.getProductsByOrderId(orderId);
    }

}

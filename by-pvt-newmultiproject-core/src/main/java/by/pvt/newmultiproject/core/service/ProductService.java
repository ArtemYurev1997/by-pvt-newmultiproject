package by.pvt.newmultiproject.core.service;

import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.dto.ProductResponse;
import by.pvt.newmultiproject.core.domain.Product;
import by.pvt.newmultiproject.core.repository.ProductRepository;
import by.pvt.newmultiproject.core.mapper.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductRepository productRepository;
    private final MappingUtils mappingUtils;

    public ProductService(ProductRepository productRepository, MappingUtils mappingUtils) {
        this.productRepository = productRepository;
        this.mappingUtils = mappingUtils;
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
}

package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.core.domain.Product;

import java.util.List;

public interface ProductRepositoryDB {
     Product addProduct(Product product);

    List<Product> updateProducts(Long id, ProductRequest productRequest);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    void addProductDB(Product product);

    Product getProductById(Long id);
}

package by.pvt.newmultiproject.api.contract;

import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.dto.ProductResponse;

import java.util.List;

public interface ProductApi {
    List<ProductResponse> findAllProducts();

    void addProduct(ProductRequest productRequest);

    void deleteProduct(Long id);

    ProductResponse findProductById(Long id);
}

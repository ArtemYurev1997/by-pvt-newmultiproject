package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.core.FileWorker;
import by.pvt.newmultiproject.core.domain.Product;
import by.pvt.newmultiproject.core.mapper.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository extends FileWorker implements ProductRepositoryDB{
    public static String FILE = "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbProducts";

    public static List<Product> products = new ArrayList<>();

    private final MappingUtils mappingUtils;

    public ProductRepository(MappingUtils mappingUtils) {
        this.mappingUtils = mappingUtils;
    }

    @Override
    public Product addProduct(Product product) {
        products = getAllProducts();
        products.add(product);
        serializeObject(products, FILE);
        return product;
    }

    @Override
    public List<Product> updateProducts(Long id, ProductRequest productRequest)  {
        Object object = deserializeObject(FILE);
        if (object instanceof List<?>) {
            products = (List<Product>) object;
        }
        Product product = getProductById(id);
        product.setName(productRequest.getName());
        product.setType(productRequest.getType());
        product.setCode(productRequest.getCode());
        product.setPrice(productRequest.getPrice());
        serializeObject(products, FILE);
        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        Object object = deserializeObject(FILE);
        List<Product> product = new ArrayList<>();
        if (object instanceof List<?>) {
            product = (List<Product>) object;
        }
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        products = getAllProducts();
        if(products.isEmpty()) {
            return;
        }
        Product product = getProductById(id);
        products.remove(product);
        for (Product product1 : products) {
            if(product1.getId() > id)
                product1.setId(product1.getId() - 1);
        }
        serializeObject(products, FILE);
        System.out.println(products.stream().map(mappingUtils::mapToProductDto).collect(Collectors.toList()));
    }

    @Override
    public void addProductDB(Product product) {

    }

    @Override
    public Product getProductById(Long id) {
        products = getAllProducts();
        return products.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

}

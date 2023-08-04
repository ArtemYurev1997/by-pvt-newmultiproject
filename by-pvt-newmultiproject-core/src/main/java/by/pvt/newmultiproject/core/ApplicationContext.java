package by.pvt.newmultiproject.core;

import by.pvt.newmultiproject.core.controller.ClientController;
import by.pvt.newmultiproject.core.controller.ProductController;
import by.pvt.newmultiproject.core.repository.ClientRepository;
import by.pvt.newmultiproject.core.repository.ProductRepository;
import by.pvt.newmultiproject.core.service.ClientService;
import by.pvt.newmultiproject.core.service.ProductService;
import by.pvt.newmultiproject.core.utils.MappingUtils;

public class ApplicationContext {
    private final MappingUtils mappingUtils = new MappingUtils();
    private final ClientRepository clientRepository = new ClientRepository(mappingUtils);
    private final ProductRepository productRepository = new ProductRepository(mappingUtils);
    private final ClientService clientService = new ClientService(clientRepository, mappingUtils);
    private final ProductService productService = new ProductService(productRepository, mappingUtils);

    private static ApplicationContext applicationContext;
    private static ClientController clientController;
    private static ProductController productController;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if(applicationContext == null) {
            return new ApplicationContext();
        }
        return applicationContext;
    }

    public ClientController getClientController() {
        if(clientController == null) {
            return new ClientController(clientService);
        }
        return clientController;
    }

    public ProductController getProductController() {
        if(productController == null) {
            return new ProductController(productService);
        }
        return productController;
    }
}

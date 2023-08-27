package by.pvt.newmultiproject.core;

import by.pvt.newmultiproject.core.controller.BasketController;
import by.pvt.newmultiproject.core.controller.ClientController;
import by.pvt.newmultiproject.core.controller.OrderController;
import by.pvt.newmultiproject.core.controller.ProductController;
import by.pvt.newmultiproject.core.repository.BasketRepository;
import by.pvt.newmultiproject.core.repository.ClientRepository;
import by.pvt.newmultiproject.core.repository.OrderRepository;
import by.pvt.newmultiproject.core.repository.ProductRepository;
import by.pvt.newmultiproject.core.service.BasketService;
import by.pvt.newmultiproject.core.service.ClientService;
import by.pvt.newmultiproject.core.service.OrderService;
import by.pvt.newmultiproject.core.service.ProductService;
import by.pvt.newmultiproject.core.mapper.MappingUtils;

public class ApplicationContext {
    private final MappingUtils mappingUtils = new MappingUtils();
    private final ClientRepository clientRepository = new ClientRepository(mappingUtils);
    private final ProductRepository productRepository = new ProductRepository(mappingUtils);
    private final BasketRepository basketRepository = new BasketRepository();
    private final ClientService clientService = new ClientService(clientRepository, mappingUtils);
    private final ProductService productService = new ProductService(productRepository, mappingUtils, basketRepository);
    private final BasketRepository bucketRepository = new BasketRepository();
    private final BasketService basketService = new BasketService(bucketRepository, productRepository, mappingUtils);
    private final OrderRepository orderRepository = new OrderRepository();
    private final OrderService orderService = new OrderService(bucketRepository, productRepository, orderRepository, mappingUtils, productService);

    private static ApplicationContext applicationContext;
    private static ClientController clientController;
    private static ProductController productController;
    private static BasketController basketController;
    private static OrderController orderController;

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

    public BasketController getBasketController() {
        if(basketController == null) {
            return new BasketController(basketService, mappingUtils);
        }
        return basketController;
    }

    public OrderController getOrderController() {
        if(orderController == null) {
            return new OrderController(orderService, mappingUtils);
        }
        return orderController;
    }
}

package by.pvt.newmultiproject.core;

import by.pvt.newmultiproject.core.config.PostgreConnection;
import by.pvt.newmultiproject.core.controller.BasketController;
import by.pvt.newmultiproject.core.controller.ClientController;
import by.pvt.newmultiproject.core.controller.OrderController;
import by.pvt.newmultiproject.core.controller.ProductController;
import by.pvt.newmultiproject.core.mapper.*;
import by.pvt.newmultiproject.core.repository.BasketRepository;
import by.pvt.newmultiproject.core.repository.ClientRepository;
import by.pvt.newmultiproject.core.repository.OrderRepository;
import by.pvt.newmultiproject.core.repository.ProductRepository;
import by.pvt.newmultiproject.core.repository.impl.BasketRepositoryDBImpl;
import by.pvt.newmultiproject.core.repository.impl.ClientRepositoryDBImpl;
import by.pvt.newmultiproject.core.repository.impl.OrderRepositoryDBImpl;
import by.pvt.newmultiproject.core.repository.impl.ProductRepositoryDBImpl;
import by.pvt.newmultiproject.core.service.BasketService;
import by.pvt.newmultiproject.core.service.ClientService;
import by.pvt.newmultiproject.core.service.OrderService;
import by.pvt.newmultiproject.core.service.ProductService;

public class ApplicationContext {
    private final MappingUtils mappingUtils = new MappingUtils();
    private final PostgreConnection postgreConnection = new PostgreConnection();
    private final MapperClientDB mapperDB = new MapperClientDB();
    private final MapperProductDB mapperProductDB = new MapperProductDB();
    private final MapperOrderDB mapperOrderDB = new MapperOrderDB();
    private final MapperBasketDB mapperBasketDB = new MapperBasketDB();

    private final ClientRepository clientRepository = new ClientRepository(mappingUtils);

    private final ClientRepositoryDBImpl clientRepositoryDB = new ClientRepositoryDBImpl(postgreConnection, mapperDB);
    private final ProductRepositoryDBImpl productRepositoryDB = new ProductRepositoryDBImpl(postgreConnection, mapperProductDB);
    private final BasketRepositoryDBImpl basketRepositoryDB = new BasketRepositoryDBImpl(postgreConnection, mapperBasketDB);

    private final ProductRepository productRepository = new ProductRepository(mappingUtils);
    private final BasketRepository basketRepository = new BasketRepository();

    private final OrderRepository orderRepository = new OrderRepository();
    private final OrderRepositoryDBImpl orderRepositoryDB = new OrderRepositoryDBImpl(postgreConnection, mapperOrderDB);


    private final ClientService clientService = new ClientService(clientRepository, clientRepositoryDB, mappingUtils);
    private final ProductService productService = new ProductService(productRepositoryDB, productRepository, mappingUtils, basketRepository, basketRepositoryDB);
    private final OrderService orderService = new OrderService(basketRepository, basketRepositoryDB, productRepository, productRepositoryDB, clientRepository, clientRepositoryDB, orderRepositoryDB, orderRepository, mappingUtils, productService);
    private final BasketService basketService = new BasketService(basketRepository, productRepository, mappingUtils, productRepositoryDB, basketRepositoryDB, orderRepositoryDB, orderRepository, orderService);



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

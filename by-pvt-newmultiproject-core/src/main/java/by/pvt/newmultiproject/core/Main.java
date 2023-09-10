package by.pvt.newmultiproject.core;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.OrderRequest;
import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.api.enums.Status;
import by.pvt.newmultiproject.api.enums.TypeStuff;
import by.pvt.newmultiproject.core.controller.BasketController;
import by.pvt.newmultiproject.core.controller.ClientController;
import by.pvt.newmultiproject.core.controller.OrderController;
import by.pvt.newmultiproject.core.controller.ProductController;
import by.pvt.newmultiproject.core.domain.Basket;
import by.pvt.newmultiproject.core.domain.Order;
import by.pvt.newmultiproject.core.mapper.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        MappingUtils mappingUtils = new MappingUtils();
        ClientRequest user1 = new ClientRequest("Anton", "Tim", "Rex", "1234", Roles.CLIENT);
        ClientRequest user2 = new ClientRequest("Dmitriy", "Lixx", "Sql", "12345", Roles.CLIENT);
        ClientRequest user3 = new ClientRequest("Oleg", "Olegov", "Pork", "1221", Roles.CLIENT);
        ClientRequest user4 = new ClientRequest("Tim", "Sorokin", "Yepv", "1366", Roles.ADMIN);
        List<ClientRequest> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        for (ClientRequest user : users) {
            user.setId();
        }
        FileWorker.serializeObject(users.stream().map(mappingUtils::mapToClientEntity).collect(Collectors.toList()), "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbClient");
        FileWorker.deserializeObject("D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbClient");


        ProductRequest product1 = new ProductRequest("Milk", TypeStuff.FOOD, 57623L, 1.5);
        ProductRequest product2 = new ProductRequest("Shampoo", TypeStuff.COSMETICS, 89714L, 8.3);
        ProductRequest product3 = new ProductRequest("Toothpaste", TypeStuff.COSMETICS, 70039L, 2.5);
        ProductRequest product4 = new ProductRequest("Hammer", TypeStuff.INSTRUMENTS, 934652L, 10.8);
        ProductRequest product5 = new ProductRequest("Cheese", TypeStuff.FOOD, 15900L, 3.7);
        ProductRequest product6 = new ProductRequest("Battery", TypeStuff.INSTRUMENTS, 88631L, 1.8);
        ProductRequest product7 = new ProductRequest("Bread", TypeStuff.FOOD, 10104L, 2.0);
        List<ProductRequest> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        for (ProductRequest product : products) {
            product.setId();
        }

        FileWorker.serializeObject(products.stream().map(mappingUtils::mapToProductEntity).collect(Collectors.toList()), "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbProducts");
        FileWorker.deserializeObject("D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbProducts");


        Order order1 = new Order(1L, 4.5,Status.UNFORMED);
        Order order2 = new Order(1L, 6.7, Status.ON_THE_WAY);
        Order order3 = new Order(1L, 6.7, Status.DONE);
        Order order4 = new Order(2L, 6.7, Status.UNFORMED);
        Order order5 = new Order(2L, 6.7, Status.ON_THE_WAY);
        Order order6 = new Order(2L, 6.7, Status.WAITING_FOR_THE_COURIER);
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        for(Order order: orders) {
            order.setId();
        }
        FileWorker.serializeObject(orders, "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbOrder");
        FileWorker.deserializeObject("D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbOrder");



        Basket basket1 = new Basket(1L, 1L, 1L, 2);
        Basket basket2 = new Basket(2L, 2L, 1L, 1);
        Basket basket3 = new Basket(3L, 3L, 1L, 1);
        Basket basket4 = new Basket(4L, 1L, 2L, 1);
        Basket basket5 = new Basket(5L, 2L, 2L, 2);
        Basket basket6 = new Basket(6L, 3L, 2L, 3);
        List<Basket> baskets = new ArrayList<>();
        baskets.add(basket1);
        baskets.add(basket2);
        baskets.add(basket3);
        baskets.add(basket4);
        baskets.add(basket5);
        baskets.add(basket6);
        FileWorker.serializeObject(baskets, "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbBasket");
        FileWorker.deserializeObject("D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbBasket");



        ApplicationContext applicationContext = ApplicationContext.getInstance();
        ClientController clientController = applicationContext.getClientController();
        ProductController productController = applicationContext.getProductController();
        OrderController orderController = ApplicationContext.getInstance().getOrderController();
        BasketController basketController = ApplicationContext.getInstance().getBasketController();

//        System.out.println(clientController.getAllClients());
//        System.out.println(clientController.authorise("Pork", "1221"));
//        clientController.delete(2L);
//        clientController.register(new ClientRequest("Vasya", "Popov", "Slash", "7667", Roles.CLIENT));
//        System.out.println(clientController.getAllClients());
//
//
//        System.out.println(productController.findAllProducts());
//        productController.deleteProduct(4L);
//        System.out.println(productController.findProductById(1L));
//
//        ProductRequest productRequest = new ProductRequest("Кетчуп", TypeStuff.FOOD, 2548834L, 2.6);
//        productController.addProduct(productRequest);
//        productController.deleteProduct(11L);

//        System.out.println(productController.findAllProducts());
//        System.out.println(clientController.getAllClients());
//        System.out.println(clientController.findClientById(1L));

//        clientController.addUser(new ClientRequest("Oleg", "Olegov", "Pork", "1221", Roles.CLIENT));
//        clientController.delete(11L);

//        System.out.println(orderController.getAllOrders());
//        System.out.println(orderController.getOrderByOrderId(1L));
//        System.out.println(basketController.getBucketsByOrderId(1L));
//        System.out.println(orderController.deleteProductByOrderFromBasket(1L, 1L));
//        System.out.println(orderController.getOrderByUserId(2L));

//        System.out.println(orderController.getOrderById(3L));
//        System.out.println(orderController.getOrderByUserId(1L));
//        orderController.addOrder(new OrderRequest(3L, 23.9, Status.WAITING_FOR_THE_COURIER));
//        clientController.updateDB(11L, new ClientRequest("Эмиль", "Нуриев", "Emil1414", "9775", Roles.CLIENT));
//        System.out.println(clientController.authorise("Andrew17", "5757"));
//        productController.updateDB(11L, new ProductRequest("Спагетти", TypeStuff.FOOD, 229935L, 3.5));
//        System.out.println(orderController.getOrderByUserId(3L));
//        System.out.println(basketController.getBasketByOrderId(1L));

//        System.out.println(basketController.addProduct(3L, 1L));
//        System.out.println(basketController.deleteProduct(2L, 1L));
//        clientController.addUser(new ClientRequest("Дмитрий", "Васильев", "Dima322", "3322", Roles.CLIENT));
//        System.out.println(clientController.getClient());

//        System.out.println(orderController.getOrderByOrderId(1L));
        System.out.println(orderController.deleteProductByOrderFromBasket(1L, 1L));

//        System.out.println(basketController.addProduct(4L, 2L));
//        System.out.println(basketController.addProduct(6L, 7L));

//        System.out.println(orderController.createOrder(8L, 6L));
//          System.out.println(basketController.addProduct(8L, 6L));
//        System.out.println(orderController.changeStatus(11L, 10));

    }
}

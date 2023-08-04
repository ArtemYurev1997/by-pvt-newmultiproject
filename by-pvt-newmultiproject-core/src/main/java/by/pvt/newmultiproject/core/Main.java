package by.pvt.newmultiproject.core;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.api.enums.TypeStuff;
import by.pvt.newmultiproject.core.controller.ClientController;
import by.pvt.newmultiproject.core.controller.ProductController;
import by.pvt.newmultiproject.core.utils.MappingUtils;

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
        FileWorker.serializeObject(users.stream().map(mappingUtils::mapToClientEntity).collect(Collectors.toList()), "D:\\Pvt Enterprise\\MultiProjectShop\\by-pvt-multiproject\\by-pvt-multiproject-core\\src\\main\\resources\\dbClient");
        FileWorker.deserializeObject("D:\\Pvt Enterprise\\MultiProjectShop\\by-pvt-multiproject\\by-pvt-multiproject-core\\src\\main\\resources\\dbClient");


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

        FileWorker.serializeObject(products.stream().map(mappingUtils::mapToProductEntity).collect(Collectors.toList()), "D:\\Pvt Enterprise\\MultiProjectShop\\by-pvt-multiproject\\by-pvt-multiproject-core\\src\\main\\resources\\dbProducts");
        FileWorker.deserializeObject("D:\\Pvt Enterprise\\MultiProjectShop\\by-pvt-multiproject\\by-pvt-multiproject-core\\src\\main\\resources\\dbProducts");

        ApplicationContext applicationContext = ApplicationContext.getInstance();
        ClientController clientController = applicationContext.getClientController();
        ProductController productController = applicationContext.getProductController();

        System.out.println(clientController.getAllClients());
        System.out.println(clientController.authorise("Pork", "1221"));
        clientController.delete(2L);
        clientController.register(new ClientRequest("Vasya", "Popov", "Slash", "7667", Roles.CLIENT));
        System.out.println(clientController.getAllClients());


        System.out.println(productController.findAllProducts());
        productController.deleteProduct(4L);
        System.out.println(productController.findProductById(user2.getId()));

        ProductRequest productRequest = new ProductRequest("Meat", TypeStuff.FOOD, 23657L, 6.8);
        productController.addProduct(productRequest);
        System.out.println(productController.findAllProducts());
    }
}

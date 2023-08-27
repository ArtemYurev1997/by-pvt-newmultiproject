package by.pvt.newmultiproject.core.servlets.basket;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.BasketController;
import by.pvt.newmultiproject.core.controller.ClientController;
import by.pvt.newmultiproject.core.controller.OrderController;
import by.pvt.newmultiproject.core.controller.ProductController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class BasketServletDelete extends HttpServlet {
    private final BasketController bucketController;
    private final ProductController productController;
    private final OrderController orderController;
    private final ClientController clientController;

    public BasketServletDelete() {
        clientController = ApplicationContext.getInstance().getClientController();
        orderController = ApplicationContext.getInstance().getOrderController();
        productController = ApplicationContext.getInstance().getProductController();
        bucketController = ApplicationContext.getInstance().getBasketController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var products = new ArrayList<>();
        req.setAttribute("productslist", products);
        req.getRequestDispatcher("/jsp/basketproducts.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long sessionId = Long.valueOf(req.getParameter("sessionId"));
        Long productId = Long.valueOf(req.getParameter("productId"));
        var product = bucketController.deleteProduct(productId, sessionId);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/jsp/basketproducts.jsp").forward(req, resp);
    }
}

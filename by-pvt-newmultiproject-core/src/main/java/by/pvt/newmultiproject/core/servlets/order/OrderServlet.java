package by.pvt.newmultiproject.core.servlets.order;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.BasketController;
import by.pvt.newmultiproject.core.controller.OrderController;
import by.pvt.newmultiproject.core.controller.ProductController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OrderServlet extends HttpServlet {
    private final BasketController bucketController;
    private final ProductController productController;
    private final OrderController orderController;

    public OrderServlet() {
        orderController = ApplicationContext.getInstance().getOrderController();
        productController = ApplicationContext.getInstance().getProductController();
        bucketController = ApplicationContext.getInstance().getBasketController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("id"));
        var order = orderController.getOrderByOrderId(orderId);
        req.setAttribute("order", order);
        req.getRequestDispatcher("/jsp/orderproducts.jsp").forward(req, resp);
    }
}

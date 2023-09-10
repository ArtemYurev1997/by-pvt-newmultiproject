package by.pvt.newmultiproject.core.servlets.order;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.OrderController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OrderServletGetAll extends HttpServlet {
    private final OrderController orderController;

    public OrderServletGetAll() {
        orderController = ApplicationContext.getInstance().getOrderController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var orders = orderController.getAllOrders1();
        req.setAttribute("order", orders);
        req.getRequestDispatcher("/jsp/orderproducts.jsp").forward(req, resp);
    }
}

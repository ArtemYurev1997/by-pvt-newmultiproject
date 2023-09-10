package by.pvt.newmultiproject.core.servlets.order;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.BasketController;
import by.pvt.newmultiproject.core.controller.OrderController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OrderServletFindByOrderId extends HttpServlet {
    private final OrderController orderController;
    private final BasketController basketController;

    public OrderServletFindByOrderId() {
        basketController = ApplicationContext.getInstance().getBasketController();
        orderController = ApplicationContext.getInstance().getOrderController();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        var orderDto = orderController.getOrderByOrderId(orderId);
        req.setAttribute("orderDto", orderDto);
        req.getRequestDispatcher("/jsp/orderdto.jsp").forward(req, resp);
    }
}

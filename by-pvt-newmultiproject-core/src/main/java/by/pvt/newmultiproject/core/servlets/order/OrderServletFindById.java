package by.pvt.newmultiproject.core.servlets.order;


import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.OrderController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OrderServletFindById extends HttpServlet {
    private final OrderController orderController;

    public OrderServletFindById() {
        orderController = ApplicationContext.getInstance().getOrderController();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        var order = orderController.getOrderById(id);
        req.setAttribute("orders", order);
        req.getRequestDispatcher("/jsp/orderid.jsp").forward(req, resp);
    }
}

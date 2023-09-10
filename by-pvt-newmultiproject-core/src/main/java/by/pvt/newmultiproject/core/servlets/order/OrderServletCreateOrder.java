package by.pvt.newmultiproject.core.servlets.order;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.BasketController;
import by.pvt.newmultiproject.core.controller.OrderController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class OrderServletCreateOrder extends HttpServlet {
    private final OrderController orderController;
    private final BasketController basketController;

    public OrderServletCreateOrder() {
        basketController = ApplicationContext.getInstance().getBasketController();
        orderController = ApplicationContext.getInstance().getOrderController();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var session = req.getSession(true);
//        String sessionIds = session.getId();
        Long productId = Long.valueOf(req.getParameter("productId"));
        Long sessionId = Long.valueOf(req.getParameter("sessionId"));
        var orderResponse = basketController.addProduct(productId, sessionId);
        req.setAttribute("newOrder", orderResponse);
        req.getRequestDispatcher("/jsp/createorder.jsp").forward(req, resp);

    }
}

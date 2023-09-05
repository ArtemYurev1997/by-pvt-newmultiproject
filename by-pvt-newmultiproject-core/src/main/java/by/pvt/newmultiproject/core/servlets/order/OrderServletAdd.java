package by.pvt.newmultiproject.core.servlets.order;

import by.pvt.newmultiproject.api.dto.OrderRequest;
import by.pvt.newmultiproject.api.dto.OrderResponse;
import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.enums.Status;
import by.pvt.newmultiproject.api.enums.TypeStuff;
import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.BasketController;
import by.pvt.newmultiproject.core.controller.OrderController;
import by.pvt.newmultiproject.core.controller.ProductController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OrderServletAdd extends HttpServlet {

    private final OrderController orderController;

    public OrderServletAdd() {
        orderController = ApplicationContext.getInstance().getOrderController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var orders = orderController.getAllOrders1();
        req.setAttribute("order", orders);
        req.getRequestDispatcher("/jsp/orderproducts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Long userId = Long.valueOf(req.getParameter("userId"));
        Double costs = Double.valueOf(req.getParameter("costs"));
        OrderRequest orderRequest;
        if (req.getParameter("status").equals("Unformed")) {
            orderRequest = new OrderRequest(id, userId, costs, Status.UNFORMED);
        } else if (req.getParameter("status").equals("On the way")) {
            orderRequest = new OrderRequest(id, userId, costs, Status.ON_THE_WAY);
        } else if (req.getParameter("status").equals("Waiting")) {
            orderRequest = new OrderRequest(id, userId, costs, Status.WAITING_FOR_THE_COURIER);
        }
        else {
            orderRequest = new OrderRequest(id, userId, costs, Status.DONE);
        }
        orderController.addOrder(orderRequest);
        doGet(req, resp);
    }
}

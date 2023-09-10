package by.pvt.newmultiproject.core.servlets.order;

import by.pvt.newmultiproject.api.dto.OrderRequest;
import by.pvt.newmultiproject.api.enums.Status;
import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.OrderController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OrderServletUpdate extends HttpServlet {
    private final OrderController orderController;

    public OrderServletUpdate() {
        orderController = ApplicationContext.getInstance().getOrderController();
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
        orderController.update(id, orderRequest);
        req.setAttribute("order", orderController.getAllOrders1());
        req.getRequestDispatcher("/jsp/orderproducts.jsp").forward(req, resp);
    }
}

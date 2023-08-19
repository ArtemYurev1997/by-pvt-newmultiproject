package by.pvt.newmultiproject.core.servlets.client;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.ClientController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ClientServletFindById extends HttpServlet {
    private final ClientController clientController;


    public ClientServletFindById() {
        clientController = ApplicationContext.getInstance().getClientController();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        var user = clientController.findClientById(id);
        req.setAttribute("client", user);
        req.getRequestDispatcher("/jsp/clientid.jsp").forward(req, resp);
    }
}

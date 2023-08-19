package by.pvt.newmultiproject.core.servlets.client;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.ClientController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ClientServletDelete extends HttpServlet {
    private final ClientController clientController;


    public ClientServletDelete() {
        clientController = ApplicationContext.getInstance().getClientController();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        clientController.delete(id);
        var clients = clientController.getAllClients();
        req.setAttribute("users", clients);
        req.getRequestDispatcher("/jsp/client.jsp").forward(req, resp);
    }
}

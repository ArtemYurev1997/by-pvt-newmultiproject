package by.pvt.newmultiproject.core.servlets.client;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.ClientController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ClientServletUpdate extends HttpServlet {
    private final ClientController clientController;

    public ClientServletUpdate() {
        clientController = ApplicationContext.getInstance().getClientController();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ClientRequest clientRequest;
        if(req.getParameter("role").equals("Admin")){
            clientRequest = new ClientRequest(name, surname, login, password, Roles.ADMIN);
        }
        else {
            clientRequest = new ClientRequest(name, surname, login, password, Roles.CLIENT);
        }
          clientController.updateDB(id, clientRequest);
        req.setAttribute("users", clientController.getAllClients());
        req.getRequestDispatcher("/jsp/client.jsp").forward(req, resp);
    }
}

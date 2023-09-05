package by.pvt.newmultiproject.core.servlets;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.ClientResponse;
import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.ClientController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    private final ClientController clientController;


    public RegistrationServlet() {
        clientController = ApplicationContext.getInstance().getClientController();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var clients = clientController.getAllClients();
//        req.setAttribute("clients", clients);
//        req.getRequestDispatcher("/jsp/clientlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ClientResponse user;
        if (req.getParameter("role").equals("Admin")) {
            ClientRequest clientRequest = new ClientRequest(name, surname, login, password, Roles.ADMIN);
            clientController.addUser(clientRequest);
            user = clientController.getClient();
//            user = clientController.register(clientRequest);
        } else {
            ClientRequest clientRequest = new ClientRequest(name, surname, login, password, Roles.CLIENT);
            clientController.addUser(clientRequest);
            user = clientController.getClient();
//            user = clientController.register(clientRequest);
        }

        var session = req.getSession(true);
        session.setAttribute("clientAuthorise", user);
        req.setAttribute("user", user);

        if (user.getRole().equals(Roles.ADMIN)) {
            req.getRequestDispatcher("/jsp/adminpage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/jsp/clientpage.jsp").forward(req, resp);
        }
    }
}
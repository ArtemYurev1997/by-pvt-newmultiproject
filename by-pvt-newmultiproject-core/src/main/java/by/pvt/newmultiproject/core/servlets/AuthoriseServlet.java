package by.pvt.newmultiproject.core.servlets;

import by.pvt.newmultiproject.api.dto.ClientResponse;
import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.ClientController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthoriseServlet extends HttpServlet {
    private final ClientController clientController;


    public AuthoriseServlet() {
        clientController = ApplicationContext.getInstance().getClientController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var users = clientController.getAllClients();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/jsp/client.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            ClientResponse clientResponse = clientController.authorise(login, password);
            if(clientResponse != null) {
                req.setAttribute("clientResponse", clientResponse);
            }
            else {
                String message = "Error page My404";
                req.setAttribute("message", message);
            }

            var session = req.getSession(true);
            session.setAttribute("clientAuthorise", clientResponse);
            req.setAttribute("user", clientResponse);

            if(clientResponse.getRole().equals(Roles.ADMIN)) {
                req.getRequestDispatcher("jsp/adminpage.jsp").forward(req, resp);
            }
            else {
                req.getRequestDispatcher("jsp/clientpage.jsp").forward(req, resp);
            }
        }
        catch(Throwable e) {
            req.setAttribute("clientResponse1", "Not found client with login: " + login + " and password: " + password + "!");
            req.getRequestDispatcher("/jsp/authorise.jsp").forward(req, resp);
        }
    }
}

package by.pvt.newmultiproject.core.servlets;

import by.pvt.newmultiproject.api.dto.ClientResponse;
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
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            ClientResponse clientResponse = clientController.authorise(login, password);
            if (clientResponse != null) {
                req.setAttribute("clientResponse", clientResponse);
            } else {
                String message = "Error page My404";
                req.setAttribute("message", message);
            }
            req.getRequestDispatcher("/jsp/clientauthorise.jsp").forward(req, resp);
        } catch (Throwable e) {
            req.setAttribute("clientResponse1", "Not found client with login: " + login + " and password: " + password + "!");
            req.getRequestDispatcher("/jsp/authorise.jsp").forward(req, resp);
        }
    }
}

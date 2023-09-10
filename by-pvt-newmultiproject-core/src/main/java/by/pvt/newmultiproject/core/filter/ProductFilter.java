package by.pvt.newmultiproject.core.filter;

import by.pvt.newmultiproject.api.dto.ClientResponse;
import by.pvt.newmultiproject.api.enums.Roles;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ProductFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession(false);
        if(httpSession == null) {
            httpServletRequest.setAttribute("errorMessage", "Пройдите аутентификацию снова!");
            httpServletRequest.getRequestDispatcher("jsp/error.jsp").forward(servletRequest, servletResponse);
        }
        ClientResponse clientResponse = (ClientResponse) httpSession.getAttribute("clientAuthorise");
        if(clientResponse.getRole().equals(Roles.CLIENT)) {
            httpServletRequest.setAttribute("errorMessage", "У вас недостаточно прав! Вы клиент!");
            httpServletRequest.getRequestDispatcher("jsp/error.jsp").forward(servletRequest, servletResponse);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}

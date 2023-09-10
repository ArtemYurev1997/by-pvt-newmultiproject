package by.pvt.newmultiproject.core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession == null || httpSession.getAttribute("clientAuthorise") == null) {
            httpServletRequest.setAttribute("errorMessage", "Пройдите аутентификацию снова!");
            httpServletRequest.getRequestDispatcher("jsp/error.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}

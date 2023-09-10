package by.pvt.newmultiproject.core.servlets.product;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.ProductController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProductServletGetAll extends HttpServlet {
    private final ProductController productController;

    public ProductServletGetAll() {
        productController = ApplicationContext.getInstance().getProductController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var products = productController.findAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/jsp/product.jsp").forward(req, resp);
    }
}

package by.pvt.newmultiproject.core.servlets.product;

import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.ProductController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProductServletDelete extends HttpServlet {
    private final ProductController productController;

    public ProductServletDelete() {
        productController = ApplicationContext.getInstance().getProductController();
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        productController.deleteProduct(id);
        var products = productController.findAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/jsp/product.jsp").forward(req, resp);
    }
}

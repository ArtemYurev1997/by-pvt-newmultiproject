package by.pvt.newmultiproject.core.servlets.product;

import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.enums.TypeStuff;
import by.pvt.newmultiproject.core.ApplicationContext;
import by.pvt.newmultiproject.core.controller.ProductController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProductServletAdd extends HttpServlet {
    private final ProductController productController;

    public ProductServletAdd() {
        productController = ApplicationContext.getInstance().getProductController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var products = productController.findAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/jsp/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Long code = Long.valueOf(req.getParameter("code"));
        Double price = Double.valueOf(req.getParameter("price"));
        ProductRequest productRequest = null;
        if (req.getParameter("type").equals("Food")) {
            productRequest = new ProductRequest(name, TypeStuff.FOOD, code, price);
        } else if (req.getParameter("type").equals("Instruments")) {
            productRequest = new ProductRequest(name, TypeStuff.INSTRUMENTS, code, price);
        } else if (req.getParameter("type").equals("Cosmetics")) {
            productRequest = new ProductRequest(name, TypeStuff.COSMETICS, code, price);
        }

        productController.addProduct(productRequest);
        doGet(req, resp);
    }
}

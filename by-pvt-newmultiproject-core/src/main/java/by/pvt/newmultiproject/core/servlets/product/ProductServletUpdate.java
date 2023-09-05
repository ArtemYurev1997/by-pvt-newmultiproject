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

public class ProductServletUpdate extends HttpServlet {
    private final ProductController productController;

    public ProductServletUpdate() {
        productController = ApplicationContext.getInstance().getProductController();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        Long code = Long.valueOf(req.getParameter("code"));
        Double price = Double.valueOf(req.getParameter("price"));
        ProductRequest productRequest;
        if(req.getParameter("type").equals("Food")) {
            productRequest = new ProductRequest(name, TypeStuff.FOOD, code, price);
        }
        else if(req.getParameter("type").equals("Instruments")) {
            productRequest = new ProductRequest(name, TypeStuff.INSTRUMENTS, code, price);
        }
        else {
            productRequest = new ProductRequest(name, TypeStuff.COSMETICS, code, price);
        }
        productController.updateDB(id, productRequest);
        req.setAttribute("products", productController.findAllProducts());
        req.getRequestDispatcher("/jsp/product.jsp").forward(req, resp);
    }
}

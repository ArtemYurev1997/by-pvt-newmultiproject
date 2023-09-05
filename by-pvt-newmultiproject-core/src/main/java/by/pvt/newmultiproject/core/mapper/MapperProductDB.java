package by.pvt.newmultiproject.core.mapper;

import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.api.enums.TypeStuff;
import by.pvt.newmultiproject.core.domain.Client;
import by.pvt.newmultiproject.core.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperProductDB {
    private final String id = "id";
    private final String name = "name";
    private final String type = "type_product";
    private final String code = "code";
    private final String price = "price";

    public Product mapResultSetToProduct(ResultSet resultSet) {
        Product product = new Product();
        try{
            product.setId(resultSet.getLong(id));
            product.setName(resultSet.getString(name));
            String typeProduct = resultSet.getString(type);
            product.setCode(Long.valueOf(resultSet.getString(code)));
            product.setPrice(Double.valueOf(resultSet.getString(price)));
            if(typeProduct.equals("Еда")) {
                product.setType(TypeStuff.FOOD);
            }
            else if(typeProduct.equals("Стройматериалы")) {
                product.setType(TypeStuff.INSTRUMENTS);
            }
            else {
                product.setType(TypeStuff.COSMETICS);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}

package by.pvt.newmultiproject.core.mapper;

import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.core.domain.Basket;


import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperBasketDB {
    private final String id = "id";
    private final String productId = "product_id";
    private final String orderId = "order_id";
    private final String count = "count";


    public Basket mapResultSetToBasket(ResultSet resultSet) {
        Basket basket = new Basket();
        try{
            basket.setId(resultSet.getLong(id));
            basket.setProductId(Long.valueOf(resultSet.getString(productId)));
            basket.setOrderId(Long.valueOf(resultSet.getString(orderId)));
            basket.setCount(Integer.valueOf(resultSet.getString(count)));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return basket;
    }
}

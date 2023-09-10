package by.pvt.newmultiproject.core.repository.impl;

import by.pvt.newmultiproject.core.config.JdbcConnection;
import by.pvt.newmultiproject.core.domain.Basket;
import by.pvt.newmultiproject.core.mapper.MapperBasketDB;
import by.pvt.newmultiproject.core.repository.BasketRepositoryDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketRepositoryDBImpl implements BasketRepositoryDB {
    private final String ADD_BASKET = "insert into shopsch.basket (id, product_id, order_id, count) " +
            "values (?,?,?,?)";
    private final String MAX_ID = "select max(id) from shopsch.basket";

    private final JdbcConnection jdbcConnection;
    private final MapperBasketDB mapperBasketDB;

    public BasketRepositoryDBImpl(JdbcConnection jdbcConnection, MapperBasketDB mapperBasketDB) {
        this.jdbcConnection = jdbcConnection;
        this.mapperBasketDB = mapperBasketDB;
    }

    @Override
    public List<Basket> getAllBaskets() {
        Connection connection = null;
        List<Basket> baskets = new ArrayList<>();
        try {
            connection = jdbcConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("select * from shopsch.basket");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String userId = resultSet.getString(2);
                String orderId = resultSet.getString(3);
                String count = resultSet.getString(4);
                Basket basket = new Basket(Long.valueOf(id), Long.valueOf(userId), Long.valueOf(orderId), Integer.valueOf(count));
                baskets.add(basket);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return baskets;
    }



    @Override
    public void add(Basket basket) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatementMaxId = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMaxId.executeQuery();
            resultSet.next();
            var maxId = resultSet.getLong(1);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BASKET);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setLong(2, basket.getProductId());
            preparedStatement.setLong(3, basket.getOrderId());
            preparedStatement.setInt(4, basket.getCount());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateBasket(Long id, Basket basket) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopsch.basket SET product_id=?, order_id=?, count=? WHERE id=?");
            preparedStatement.setLong(4, id);
            preparedStatement.setLong(1, basket.getProductId());
            preparedStatement.setLong(2, basket.getOrderId());
            preparedStatement.setInt(3, basket.getCount());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Basket getBasketById(Long id) {
        Connection connection = null;
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.basket b where b.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Basket();
            }
            resultSet.next();
            return mapperBasketDB.mapResultSetToBasket(resultSet);
        }
        catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from shopsch.basket where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Basket> getBasketsByOrderId(Long orderId) {
        Connection connection;
        List<Basket> baskets = new ArrayList<>();
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.basket b where b.order_id=?");
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                Long productIds = resultSet.getLong(2);
                Long orderIds = resultSet.getLong(3);
                Integer count = resultSet.getInt(4);
                Basket basket = new Basket(id, productIds, orderIds, count);
                baskets.add(basket);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return baskets;
    }

    @Override
    public Basket getBasketByOrderId(Long orderId) {
        Connection connection = null;
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.basket b where b.order_id=?");
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Basket();
            }
            resultSet.next();
            return mapperBasketDB.mapResultSetToBasket(resultSet);
        }
        catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            try{
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Long> getProductIdByBucketList(Long orderId) {
        Connection connection;
        List<Long> productsIdsList = new ArrayList<>();
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select product_id from shopsch.basket b where b.order_id=?");
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Long productIds = resultSet.getLong(2);


                productsIdsList.add(productIds);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return productsIdsList;
    }
}

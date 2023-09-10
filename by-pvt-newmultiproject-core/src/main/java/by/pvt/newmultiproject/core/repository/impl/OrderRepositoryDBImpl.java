package by.pvt.newmultiproject.core.repository.impl;

import by.pvt.newmultiproject.api.enums.Status;
import by.pvt.newmultiproject.core.config.JdbcConnection;
import by.pvt.newmultiproject.core.domain.Order;
import by.pvt.newmultiproject.core.mapper.MapperOrderDB;
import by.pvt.newmultiproject.core.repository.OrderRepositoryDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryDBImpl implements OrderRepositoryDB {
    private final String ADD_ORDER = "insert into shopsch.order (id, user_id, costs, status) " +
            "values (?,?,?,?)";
    private final String MAX_ID = "select max(id) from shopsch.order";


    private final JdbcConnection jdbcConnection;
    private final MapperOrderDB mapperOrderDB;

    public OrderRepositoryDBImpl(JdbcConnection jdbcConnection, MapperOrderDB mapperOrderDB) {
        this.jdbcConnection = jdbcConnection;
        this.mapperOrderDB = mapperOrderDB;
    }

    @Override
    public List<Order> getAllOrders() {
        Connection connection = null;
        List<Order> orders = new ArrayList<>();
        try {
            connection = jdbcConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("select * from shopsch.order");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String userId = resultSet.getString(2);
                String costs = resultSet.getString(3);
                String status = resultSet.getString(4);
                Order order;
                if(status.equals("Unformed")) {
                    order = new Order(Long.valueOf(id), Long.valueOf(userId), Double.valueOf(costs), Status.UNFORMED);
                }
                else if(status.equals("Waiting")) {
                    order = new Order(Long.valueOf(id), Long.valueOf(userId), Double.valueOf(costs), Status.WAITING_FOR_THE_COURIER);
                }
                else if(status.equals("On the way")) {
                    order = new Order(Long.valueOf(id), Long.valueOf(userId), Double.valueOf(costs), Status.ON_THE_WAY);
                }
                else {
                    order = new Order(Long.valueOf(id), Long.valueOf(userId), Double.valueOf(costs), Status.DONE);
                }
                orders.add(order);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrder() {
        Connection connection;
        Order order = null;
        try {
            connection = jdbcConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(MAX_ID);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                order = getOrderById(Long.valueOf(id));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return order;
    }


    @Override
    public Order getOrderById(Long id) {
        Connection connection = null;
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.order o where o.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Order();
            }
            resultSet.next();
            return mapperOrderDB.mapResultSetToOrder(resultSet);
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
            PreparedStatement preparedStatement = connection.prepareStatement("delete from shopsch.order where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addOrder(Order order) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatementMaxId = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMaxId.executeQuery();
            resultSet.next();
            var maxId = resultSet.getLong(1);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setLong(2, order.getUserId());
            preparedStatement.setDouble(3, order.getCost());
            preparedStatement.setString(4, String.valueOf(order.getStatus()));
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Order order) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopsch.order SET user_id=?, costs=?, status=?  WHERE id=?");
            preparedStatement.setLong(4, id);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setDouble(2, order.getCost());
            preparedStatement.setString(3, String.valueOf(order.getStatus()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        Connection connection;
        List<Order> orders = new ArrayList<>();
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.order o where o.user_id=?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String userIds = resultSet.getString(2);
                String costs = resultSet.getString(3);
                String status = resultSet.getString(4);
                Order order;
                if (status.equals("Unformed")) {
                    order = new Order(Long.valueOf(id), Long.valueOf(userIds), Double.valueOf(costs), Status.UNFORMED);
                } else if (status.equals("Waiting")) {
                    order = new Order(Long.valueOf(id), Long.valueOf(userIds), Double.valueOf(costs), Status.WAITING_FOR_THE_COURIER);
                } else if (status.equals("On the way")) {
                    order = new Order(Long.valueOf(id), Long.valueOf(userIds), Double.valueOf(costs), Status.ON_THE_WAY);
                } else {
                    order = new Order(Long.valueOf(id), Long.valueOf(userIds), Double.valueOf(costs), Status.DONE);
                }
                orders.add(order);
            }
        }
            catch(SQLException e){
                e.printStackTrace();
            }
            return orders;
    }

    @Override
    public Order getOrderByUserId(Long userId) {
        Connection connection = null;
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.order o where o.user_id=?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Order();
            }
            resultSet.next();
            return mapperOrderDB.mapResultSetToOrder(resultSet);
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
    public Order add(Order order) {
        return null;
    }

    @Override
    public Order updateOrder(Long id, Order newOrder) {
        return null;
    }
}

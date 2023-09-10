package by.pvt.newmultiproject.core.repository.impl;


import by.pvt.newmultiproject.api.dto.ProductRequest;
import by.pvt.newmultiproject.api.enums.TypeStuff;
import by.pvt.newmultiproject.core.config.JdbcConnection;
import by.pvt.newmultiproject.core.domain.Product;
import by.pvt.newmultiproject.core.mapper.MapperProductDB;
import by.pvt.newmultiproject.core.repository.ProductRepositoryDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryDBImpl implements ProductRepositoryDB {
    private final String ADD_PRODUCT = "insert into shopsch.product (id, name, type_product, code, price) " +
            "values (?,?,?,?,?)";
    private final String MAX_ID = "select max(id) from shopsch.product";

    private final JdbcConnection jdbcConnection;
    private final MapperProductDB mapperProductDB;

    public ProductRepositoryDBImpl(JdbcConnection jdbcConnection, MapperProductDB mapperProductDB) {
        this.jdbcConnection = jdbcConnection;
        this.mapperProductDB = mapperProductDB;
    }

    @Override
    public List<Product> getAllProducts() {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = jdbcConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("select * from shopsch.product");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String type = resultSet.getString(3);
                String code = resultSet.getString(4);
                String price = resultSet.getString(5);
                Product product = null;
                if(type.equals("Еда")) {
                    product = new Product(Long.valueOf(id), name, TypeStuff.FOOD, Long.valueOf(code), Double.valueOf(price));
                }
                else if(type.equals("Стройматериалы")) {
                    product = new Product(Long.valueOf(id), name, TypeStuff.INSTRUMENTS, Long.valueOf(code), Double.valueOf(price));
                }
                else {
                    product = new Product(Long.valueOf(id), name, TypeStuff.COSMETICS, Long.valueOf(code), Double.valueOf(price));
                }
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void update(Long id, ProductRequest productRequest) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopsch.product SET name=?, type_product=?, code=?, price=? WHERE id=?");
            preparedStatement.setLong(5, id);
            preparedStatement.setString(1, productRequest.getName());
            preparedStatement.setString(2, String.valueOf(productRequest.getType()));
            preparedStatement.setLong(3, productRequest.getCode());
            preparedStatement.setDouble(4, productRequest.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void deleteProduct(Long id) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from shopsch.product where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addProductDB(Product product) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatementMaxId = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMaxId.executeQuery();
            resultSet.next();
            var maxId = resultSet.getLong(1);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, String.valueOf(product.getType()));
            preparedStatement.setLong(4, product.getCode());
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product getProductById(Long id) {
        Connection connection = null;
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.product u where u.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Product();
            }
            resultSet.next();
            return mapperProductDB.mapResultSetToProduct(resultSet);
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
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> updateProducts(Long id, ProductRequest productRequest) {
        return null;
    }
}

package by.pvt.newmultiproject.core.repository.impl;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.core.config.JdbcConnection;
import by.pvt.newmultiproject.core.domain.Client;
import by.pvt.newmultiproject.core.mapper.MapperClientDB;
import by.pvt.newmultiproject.core.repository.ClientRepositoryDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryDBImpl implements ClientRepositoryDB {
    private final String ADD_USER = "insert into shopsch.user (id, name, surname, login_user, password_user, role_user) " +
            "values (?,?,?,?,?,?)";
    private final String MAX_ID = "select max(id) from shopsch.user";
    private final String NEXT_ID = "select * from shopsch.user where id=(select max(id) from shopsch.user)";


    private final JdbcConnection jdbcConnection;
    private final MapperClientDB mapperDB;

    public ClientRepositoryDBImpl(JdbcConnection jdbcConnection, MapperClientDB mapperDB) {
        this.jdbcConnection = jdbcConnection;
        this.mapperDB = mapperDB;
    }

    @Override
    public Client add(Client client) {
        return null;
    }

    @Override
    public void addUser(Client client) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatementMaxId = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMaxId.executeQuery();
            resultSet.next();
            var maxId = resultSet.getLong(1);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurname());
            preparedStatement.setString(4, client.getLogin());
            preparedStatement.setString(5, client.getPassword());
            preparedStatement.setString(6, String.valueOf(client.getRole()));
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<Client> getAllClients() {
        Connection connection;
        List<Client> clients = new ArrayList<>();
        try {
            connection = jdbcConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("select * from shopsch.user");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String login = resultSet.getString(4);
                String password = resultSet.getString(5);
                String role = resultSet.getString(6);
                Client client;
                if(role.equals("Admin")) {
                     client = new Client(Long.valueOf(id), name, surname, login, password, Roles.ADMIN);
                }
                else {
                     client = new Client(Long.valueOf(id), name, surname, login, password, Roles.CLIENT);
                }
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    public Client getClient() {
        Connection connection;
        Client client = null;
        try {
            connection = jdbcConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(NEXT_ID);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet == null) {
                return new Client();
            }
                while(resultSet.next()) {
                    String id = resultSet.getString(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    String login = resultSet.getString(4);
                    String password = resultSet.getString(5);
                    String role = resultSet.getString(6);
                    if (role.equals("Admin")) {
                        client = new Client(Long.valueOf(id), name, surname, login, password, Roles.ADMIN);
                    } else {
                        client = new Client(Long.valueOf(id), name, surname, login, password, Roles.CLIENT);
                    }
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from shopsch.user where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Client getClientById(Long id) {
        Connection connection = null;
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.user u where u.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Client();
            }
            resultSet.next();
            return mapperDB.mapResultSetToUser(resultSet);
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
    public Client checkPassword(String login, String password) {
        Connection connection = null;
        try {
            connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from shopsch.user u where u.login_user=? AND u.password_user=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Client();
            }
            resultSet.next();
            return mapperDB.mapResultSetToUser(resultSet);
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

    public void update(Long id, ClientRequest clientRequest) {
        try {
            Connection connection = jdbcConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopsch.user SET name=?, surname=?, login_user=?, password_user=?, role_user=? WHERE id=?");
            preparedStatement.setLong(6, id);
            preparedStatement.setString(1, clientRequest.getName());
            preparedStatement.setString(2, clientRequest.getSurname());
            preparedStatement.setString(3, clientRequest.getLogin());
            preparedStatement.setString(4, clientRequest.getPassword());
            preparedStatement.setString(5, String.valueOf(clientRequest.getRole()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Client> updateClients(Long id, ClientRequest clientRequest) {
        return null;
    }
}

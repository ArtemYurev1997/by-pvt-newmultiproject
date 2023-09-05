package by.pvt.newmultiproject.core.mapper;

import by.pvt.newmultiproject.api.enums.Roles;
import by.pvt.newmultiproject.core.domain.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperClientDB {
    private final String id = "id";
    private final String name = "name";
    private final String surname = "surname";
    private final String login = "login_user";
    private final String password = "password_user";
    private final String role = "role_user";

    public Client mapResultSetToUser(ResultSet resultSet) {
        Client client = new Client();
        try{
            client.setId(resultSet.getLong(id));
            client.setName(resultSet.getString(name));
            client.setSurname(resultSet.getString(surname));
            client.setLogin(resultSet.getString(login));
            String roles = resultSet.getString(role);
                if(roles.equals("Admin")) {
                client.setRole(Roles.ADMIN);
                }
                else {
                client.setRole(Roles.CLIENT);
                }
            }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
}

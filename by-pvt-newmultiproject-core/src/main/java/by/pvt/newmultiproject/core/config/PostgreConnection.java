package by.pvt.newmultiproject.core.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection implements JdbcConnection{

        @Override
        public Connection getConnection()  {
            try {
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopdb", "postgres", "sa");
                return connection;
            }
            catch (Throwable e) {
                throw new RuntimeException(e.getMessage());
            }
        }
}

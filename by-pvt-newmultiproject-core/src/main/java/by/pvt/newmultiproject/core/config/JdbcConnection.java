package by.pvt.newmultiproject.core.config;

import java.sql.Connection;

public interface JdbcConnection {
    Connection getConnection();
}

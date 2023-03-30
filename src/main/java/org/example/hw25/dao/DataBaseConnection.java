package org.example.hw25.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBaseConnection extends AutoCloseable {
    Connection getConnection() throws SQLException;
}

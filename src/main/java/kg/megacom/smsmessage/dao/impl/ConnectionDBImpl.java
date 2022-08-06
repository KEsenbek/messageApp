package kg.megacom.smsmessage.dao.impl;

import kg.megacom.smsmessage.dao.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBImpl implements ConnectionDB {
    @Override
    public Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/databases/sub-msg-db.db");
        return connection;
    }

    public void close(Connection connection) {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

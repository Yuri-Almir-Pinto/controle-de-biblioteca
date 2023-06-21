package com.controledebiblioteca.controledebiblioteca.connection;

import com.controledebiblioteca.controledebiblioteca.exception.DatabaseIntegrityException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConnectionFactory {

    private static Connection conn;
    public static Connection getConnection() {
        try {

            if (conn == null){
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_livraria", "root", "52085208");
            }

            return conn;
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void statementClose(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseIntegrityException(e.getMessage());
        }
    }

    public static void resultSetClose(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new DatabaseIntegrityException(e.getMessage());
        }
    }
}

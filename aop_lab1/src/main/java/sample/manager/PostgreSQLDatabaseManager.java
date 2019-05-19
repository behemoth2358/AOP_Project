package sample.manager;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class PostgreSQLDatabaseManager implements IDatabaseManager {
    private Connection connection;
    private String databaseName;
    private String username;
    private String userPassword;

    public PostgreSQLDatabaseManager() {}

    public PostgreSQLDatabaseManager(String databaseName, String username, String userPassword) {
        this.databaseName = databaseName;
        this.username = username;
        this.userPassword = userPassword;

        initializeConnection();
    }

    @Override
    public void initializeConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://balarama.db.elephantsql.com:5432/" + databaseName,
                    username,
                    userPassword);

        } catch (Exception e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;

        }

        if (connection == null) {
            System.out.println("Failed to make connection to the database!");
        }
    }

    @Override
    public ResultSet executeQuery(String statementStr) {
        Statement statement;
        try {
            statement = connection.createStatement();
            return statement.executeQuery(statementStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int executeUpdate(String statementStr) {
        Statement statement;

        try {
            statement = connection.createStatement();
            return statement.executeUpdate(statementStr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
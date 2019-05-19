package sample.manager;

import java.sql.ResultSet;

public interface IDatabaseManager {

    void initializeConnection();

    ResultSet executeQuery(String statement);

    int executeUpdate(String statement);

    void closeConnection();
}

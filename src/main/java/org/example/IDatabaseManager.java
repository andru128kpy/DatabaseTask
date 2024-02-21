package org.example;

import java.sql.Connection;

public interface IDatabaseManager {
    Connection connectToDataBase(String url, String name, String pass);
    void deleteRecord(Connection conn, String tableName, String columName, String valueToDelete);
    void insertRecord(Connection conn, String tableName, String[] columns, String[] values);
}

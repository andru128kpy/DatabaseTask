package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskManagerDataBase implements IDatabaseManager {
    @Override
    public Connection connectToDataBase(String url, String name, String pass) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, name, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void deleteRecord(Connection conn, String tableName, String columName, String valueToDelete) {
        try {
            String sql = "DELETE FROM " + tableName + " WHERE " + columName + " = ?";
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setString(1, valueToDelete);

            int rowsAffected = prstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Запись была успешно удалена из " + tableName);
            } else System.out.println("Запись не удалось найти " + tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertRecord(Connection conn, String tableName, String[] columns, String[] values) {
        if (columns.length != values.length) {
            System.out.println("Количество столбцов не соответствует количеству значений");
            return;
        }

        StringBuilder columnNames = new StringBuilder();
        StringBuilder valuePlaceholders = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            columnNames.append(columns[i]);
            valuePlaceholders.append("?");
            if (i < columns.length - 1) {
                columnNames.append(", ");
                valuePlaceholders.append(", ");
            }
        }

        String sql = "INSERT INTO " + tableName + " (" + columnNames.toString() + ") VALUES (" + valuePlaceholders.toString() + ")";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]);
            }

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Запись успешно добавлена в базу данных");
            } else {
                System.out.println("Ошибка при добавлении записи: запись не была добавлена");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении записи: " + e.getMessage());
        }
    }
}

package org.example;



import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/task";
        Task task1 = new Task("lk;jlkj", "jkhljhl");
        Task task2 = new Task("Нажо что-то сделать", "Важно");
        TaskManagerDataBase taskManagerDataBase = new TaskManagerDataBase();
        Connection conn = taskManagerDataBase.connectToDataBase(url, "root", "aks124alv124");

        String[] col = {"description1", "priority"};
        String[] values = {task1.getDescription(), task1.getPriority()};
        String[] vaues2 = {task2.getDescription(), task2.getPriority()};

        taskManagerDataBase.insertRecord(conn, "tasks", col, values);
        taskManagerDataBase.insertRecord(conn, "tasks", col, vaues2);
        taskManagerDataBase.deleteRecord(conn, "tasks", "description1", task2.getDescription());
        taskManagerDataBase.deleteRecord(conn, "tasks", "description1", task1.getDescription());



    }
}
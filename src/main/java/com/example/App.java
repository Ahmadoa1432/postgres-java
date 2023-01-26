package com.example;

import java.io.IOException;
import java.sql.SQLException;
public class App 
{
    public static void main(String[] args) throws IOException {
        
        
        // try {
        //     DBConnection db = DBConnection.getInstance();
        //     // Insert
        //     Task t = new Task("testt", false, "Ahmad Othman");
        //     t.insertTask();
        //     // Retrieve all tasks
        //     t.retrieveTasks();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }



        // Code for updating tasks in postgres DB.
        // try { 
        //     DBConnection db = DBConnection.getInstance();
        //     // update task
        //     Task t = new Task("Do the 2.Extra credit task AGAIN.", true, "Ahmad Othman", 6);
        //     t.updateTask();
        //     // Retrieve all tasks
        //     t.retrieveTasks();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

        // Code for deleting tasks in postgres DB.
        try {
            DBConnection db = DBConnection.getInstance();
            // delete task
            Task t = new Task(6);
            t.deleteTask();
            // Retrieve all tasks
            t.retrieveTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
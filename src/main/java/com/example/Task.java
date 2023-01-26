package com.example;

import java.io.IOException;
import java.io.Serial;
import java.sql.*;

public class Task {
    private String username;
    private String name;
    private boolean isComplete;
    private int id;

    public Task(String name, boolean isComplete, String Uname){
        this.name = name;
        this.isComplete = isComplete;
        this.username = Uname;
    }

    public Task(String name, boolean isComplete, String Uname, int id){
        this.name = name;
        this.isComplete = isComplete;
        this.username = Uname;
        this.id = id;
    }

    public Task(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String Uname){
        this.username = Uname;
    }

    public void insertTask() throws IOException{
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement insertStmt =
                    dbConnection.prepareStatement("INSERT INTO todo (task, done, username) VALUES (?, ?, ?);");
            insertStmt.setString(1, this.name);
            insertStmt.setBoolean(2, (this.isComplete));
            insertStmt.setString(3, username);
            int rows = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveTasks() throws IOException{
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            String query = "SELECT id, task, done FROM todo";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                //Display values
                String row = "ID: " + rs.getInt("id") +
                        " Task: " + rs.getString("task") +
                        " Done: " + rs.getBoolean("done") +
                        " Username: " + rs.getString("username") + "\n";
                System.out.print(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTask() throws IOException{
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement updateStmt =
                    dbConnection.prepareStatement("UPDATE todo SET task=?, done=?, username=? WHERE id=?;");
            updateStmt.setString(1, this.name);
            updateStmt.setBoolean(2, (this.isComplete));
            updateStmt.setString(3, username);
            updateStmt.setInt(4, id);
            int rows = updateStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String toString(){
        return "Task: " + this.name + "\nDone: " + (this.isComplete ? "1": "0");
    }

    public void deleteTask() throws IOException{
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement deleteStmt =
                    dbConnection.prepareStatement("DELETE FROM todo WHERE id=?;");
            deleteStmt.setInt(1, id);
            int rows = deleteStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

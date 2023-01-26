package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.io.*;

public class DBConnection {

private final String url;
  private final int port;
  private final String dbName;
  private Connection connection;

  private static DBConnection instance;

  private DBConnection() throws SQLException, IOException {
    this.dbName = "tododb";
    this.port = 5432;
    this.url = "jdbc:postgresql://localhost:" + Integer.toString(this.port) + "/" + this.dbName;
    Properties props = new Properties();

    FileReader reader = new FileReader("D:/MobileDev/demo/src/main/java/com/example/db.properties");
    Properties p = new Properties();
    p.load(reader); // reads from db.properties for login credentials

    props.setProperty("user",p.getProperty("DB_USER"));
    props.setProperty("password", p.getProperty("DB_PASSWORD"));
    props.setProperty("ssl","false");
    this.connection = DriverManager.getConnection(url, props);
  }

  public Connection getConnection() {
    return this.connection;
  }

  public static DBConnection getInstance() throws SQLException, IOException {
    if(instance == null){
      instance = new DBConnection();
    }
    else if (instance.getConnection().isClosed()) {
      instance = new DBConnection();
    }
    return instance;
  }
    
}

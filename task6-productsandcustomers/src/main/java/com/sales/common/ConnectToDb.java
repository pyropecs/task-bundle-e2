package com.sales.common;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectToDb {
    private Connection con;
    public Connection connect(){
        
        try {
            Properties props = new Properties();
            InputStream fis = getClass().getClassLoader().getResourceAsStream("db.properties");
            props.load(fis);
            String url = props.getProperty("db.url");
            String userName = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            Class.forName(props.getProperty("db.driver"));
            con = DriverManager.getConnection(url, userName, password);
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return con;
    
    }

}

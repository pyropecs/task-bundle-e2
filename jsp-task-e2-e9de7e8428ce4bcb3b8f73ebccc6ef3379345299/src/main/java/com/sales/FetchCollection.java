package com.sales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sales.common.ConnectToDb;

public class FetchCollection {

    private ResultSet rs;

    public ResultSet fetchData(String tableName, Integer... id) {
   

        String query = id.length == 0 ? "select * from " + tableName : "select * from " + tableName + " where pid= " + id[0];
        ConnectToDb connection = new ConnectToDb();
        try {
            Connection con = connection.connect();
            Statement st = con.createStatement();

            rs = st.executeQuery(query);

            st.close();
            con.close();
            return rs;

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return rs;
    }

   
}

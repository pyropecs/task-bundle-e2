package com.sales;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.sales.common.ConnectToDb;

public class DeleteCollection {
        private String result;

        public String deleteDataQuery(int id,String tableName) {
                String query = "delete from "+ tableName +" where product_id = " + id;
                try {
                        ConnectToDb connection = new ConnectToDb();
                        Connection con = connection.connect();
                        Statement st = con.createStatement();
                        st.executeUpdate(query);
                        st.close();
                        con.close();
                        result = "successfully deleted";
                } catch (SQLException e) {
                        System.out.println(e.getMessage());

                } catch (Exception e) {
                        System.out.println(e.getMessage());

                }
                return result;

        }

}

package com.sales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sales.common.ConnectToDb;

public class FetchCollection {

    private ResultSet rs;

    public ResultSet fetchData(String tableName, String... args) {

        String order = (args.length != 0) && args[1] !=null ? args[1] : "none";
        String field = (args.length != 0) && args[0] !=null? args[0] : "none";

        String orderBy = "";
        switch (order) {
            case "asc":
            case "desc":
                orderBy = " order by " + field + " " + order;
                break;
            default:
                System.out.println("no order is mentioned");
                break;
        }
        System.out.println("current table name: "+tableName);
        rs = fetchDataQuery("select * from " + tableName + orderBy);

        return rs;
    }

    public ResultSet fetchDataQuery(String query) {
        ConnectToDb connection = new ConnectToDb();

        try {
            Connection con = connection.connect();
            Statement st = con.createStatement();

            rs = st.executeQuery(query);

            st.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("something went wrong in FetchDataMethod");
            System.out.println(e.getMessage());

        }
        return rs;
    }

}

package com.sales;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sales.beanclasses.Product;

public class FetchProduct {

    public Product getProduct(int id) {
        FetchCollection fc = new FetchCollection();
        Product p = new Product();
        try {
            ResultSet rs = fc.fetchData("products", id);
            while (rs.next()) {

                p.setPid(rs.getInt("pid"));
                p.setProductName(rs.getString("product_name"));
                p.setMrp(rs.getFloat("mrp"));
                p.setQuantity(rs.getInt("quantity"));
                p.setRating(rs.getFloat("rating"));
                p.setSupplierName(rs.getString("supplier_name"));

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return p;
    }

}

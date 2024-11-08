package com.sales;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sales.beanclasses.Customer;
import com.sales.beanclasses.Product;

public class FetchData<T> {

    private ArrayList<T> arr = new ArrayList<>();
    private final String[] customerLabels = {"customer_id","customer_name","customer_age"};
    private final String[] productLabels = {"product_id","product_name","price"};
    public List<T> fetchCollection(String tableName, String... options) {
        FetchCollection fc = new FetchCollection();
    
        try {
            ResultSet rs = fc.fetchData(tableName, options);
            while (rs.next()) {
                if (tableName.equals("products")) {
                    Product productObject = new Product(rs.getInt(productLabels[0]), rs.getString(productLabels[1]), rs.getFloat(productLabels[2]));
                    arr.add((T) productObject);
                } else if (tableName.equals("customers")) {
                    {
                        Customer customerObject = new Customer(rs.getInt(customerLabels[0]), rs.getString(customerLabels[1]), rs.getInt(customerLabels[2]));
                        
                        arr.add((T) customerObject);
                    }
                } else {
                    System.out.println("something went wrong.recieved table name not yet created");
                }

            }
            rs.close();

        } catch (Exception e) {
            System.out.println("something went wrong in FetchDataClass");
            System.out.println(e.getMessage());

        }
      
        return arr;
    }

    public static String deleteProduct(int id) {
        DeleteCollection dc = new DeleteCollection();
        String result = dc.deleteDataQuery(id, "products");
        return result;
    }

}

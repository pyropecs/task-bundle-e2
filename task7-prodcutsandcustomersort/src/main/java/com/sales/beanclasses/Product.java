package com.sales.beanclasses;

public class Product {

    private int pid;
    private String productName;
    private float mrp;
    private float rating;
    private String supplierName;
    private int quantity;

    public int getPid() {
        return this.pid;
    }

    public String getProductName() {
        return this.productName;
    }

    public float getMrp() {
        return mrp;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getRating() {
        return rating;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

}

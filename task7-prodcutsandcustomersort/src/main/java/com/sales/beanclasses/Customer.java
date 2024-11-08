package com.sales.beanclasses;

public class Customer {

    private int cid;
    private String customerName;
    private String customerMobile;
    private String city;
    private int pid;

    public int getCid() {
        return cid;
    }

    public String getCity() {
        return city;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getPid() {
        return pid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

}

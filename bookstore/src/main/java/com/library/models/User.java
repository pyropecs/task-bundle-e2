package com.library.models;


public class User {
 private int id;
 private String name;
 private String department;
 private String designation;

 public int getId() {
     return id;
 }
 public String getDesignation() {
     return designation;
 }
 public String getDepartment() {
     return department;
 }

 public String getName() {
     return name;
 }


 public void setId(int id) {
     this.id = id;
 }
 public void setDepartment(String department) {
     this.department = department;
 }
 public void setDesignation(String designation) {
     this.designation = designation;
 }
 public void setName(String name) {
     this.name = name;
 }


}

package com.library.models;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;


public class User {
 private int id;
 private String name;
 private String department;
 private String designation;
 @JsonBackReference
 private Set<Book> books;
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

 public Set<Book> getBooks() {
         return books;
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

 public void setBooks(Set<Book> books) {
         this.books = books;
 }
}

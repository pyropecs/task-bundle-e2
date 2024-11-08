package com.customers.beanclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private long age;
        private double rating;

        public int getId() {
                return id;
        }

        public long getAge() {
                return age;
        }

        public String getName() {
                return name;
        }

        public double getRating() {
                return rating;
        }

        public void setAge(long age) {
                this.age = age;
        }

        public void setId(int id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setRating(double rating) {
                this.rating = rating;
        }

}

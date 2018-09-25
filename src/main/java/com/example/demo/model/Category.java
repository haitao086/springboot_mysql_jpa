package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Create table in mysql database
//DROP TABLE category;
//
//CREATE TABLE category
//(
// categoryid INT AUTO_INCREMENT PRIMARY KEY,
// categoryname VARCHAR(10) NOT NULL
//);
//
//INSERT INTO category VALUES(NULL, 'cell'), (NULL, 'book'), (NULL, 'cloth'), (NULL, 'shoe');
//
//SELECT * FROM category;
@Entity
@Table(name = "category")
public class Category {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "categoryid")
 private Integer categoryid;

 @Column(name = "categoryname")
 private String categoryname;

 public Integer getCategoryid() {
     return categoryid;
 }

 public void setCategoryid(Integer categoryid) {
     this.categoryid = categoryid;
 }

 public String getCategoryname() {
     return categoryname;
 }

 public void setCategoryname(String categoryname) {
     this.categoryname = categoryname;
 }
}
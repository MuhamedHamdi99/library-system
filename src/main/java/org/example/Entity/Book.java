package org.example.Entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Title;
    private String Author;
    private BigDecimal Price;

public Book(){}

    public Book(  String title, String author, BigDecimal price) {
        this.Id = Id;
        this.Title = title;
        this.Author = author;
        this.Price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        this.Price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + Id +
                ", title='" + Title + '\'' +
                ", auther='" + Author + '\'' +
                ", price=" + Price +
                '}';
    }
}

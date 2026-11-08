package org.example.Entity;


import jakarta.persistence.*;
import org.example.Entity.Author;
import java.math.BigDecimal;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author Author;
    private BigDecimal Price;

public Book(){}

    public Book(  String title, Author author, BigDecimal price) {
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

    public  Author getAuthor() {
        return Author;
    }

    public void setAuthor( Author author) {
        Author = author;
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

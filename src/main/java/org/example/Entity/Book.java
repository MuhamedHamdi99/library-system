package org.example.Entity;

public class Book {
    private int Id;
    private String Title;
    private String Author;
    private double Price;

public Book(){}

    public Book(  String title, String author, double price) {
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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
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

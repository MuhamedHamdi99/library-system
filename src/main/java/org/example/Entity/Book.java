package org.example.Entity;

public class Book {
    private int id;
    private String title;
    private String auther;
    private double price;

public Book(){}

    public Book(  String title, String auther, double price) {
        this.id = id;
        this.title = title;
        this.auther = auther;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", auther='" + auther + '\'' +
                ", price=" + price +
                '}';
    }
}

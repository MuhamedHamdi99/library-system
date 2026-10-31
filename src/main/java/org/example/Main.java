package org.example;

import org.example.DAO.BookDAO;
import org.example.Entity.Book;

import java.sql.Connection;
import java.sql.DriverManager;


public class Main {
    public static void main(String[] args) {

        Book book1 = new Book("War","Muhamed",100);
        Book book2 = new Book("War2","Ahmed",400);
        Book book3 = new Book("War2","Hadi",700);

        BookDAO bookDAO = new BookDAO();
        bookDAO.findAll().forEach(System.out::println);






    }
}
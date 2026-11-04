package org.example;

import org.example.DAO.BookDAO;
import org.example.Entity.Book;

public class Main {
    public static void main(String[] args) {

        Book book1 = new Book("War","Muhamed",100);
        Book book2 = new Book("War2","Ahmed",400);
        Book book3 = new Book("War2","Hadi",700);

        BookDAO bookDAO = new BookDAO();
        bookDAO.insertBook(book1);
        bookDAO.insertBook(book2);
        bookDAO.insertBook(book3);

        bookDAO.findAll().forEach(System.out::println);
        System.out.println("Book of id=2 is : " + bookDAO.findById(2));


        Book b = bookDAO.findById(1);
        b.setPrice(500);
        System.out.println(bookDAO.updateBook(b));
        System.out.println(bookDAO.findById(1));



    }
}
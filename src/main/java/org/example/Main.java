package org.example;

import org.example.DAO.BookDAO;
import org.example.Entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {

//        Book book1 = new Book("War","Muhamed",100);
//        Book book2 = new Book("War2","Ahmed",400);
//        Book book3 = new Book("War2","Hadi",700);
//
//        BookDAO bookDAO = new BookDAO();
//        bookDAO.insertBook(book1);
//        bookDAO.insertBook(book2);
//        bookDAO.insertBook(book3);
//
//        bookDAO.findAll().forEach(System.out::println);
//        System.out.println("Book of id=2 is : " + bookDAO.findById(2));
//
//
//        Book b = bookDAO.findById(1);
//        b.setPrice(500);
//        System.out.println(bookDAO.updateBook(b));
//        System.out.println(bookDAO.findById(1));
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(new Book("War2", "Hadi", new BigDecimal("199.00")));
            tx.commit();
        }

        sf.close();


    }
}
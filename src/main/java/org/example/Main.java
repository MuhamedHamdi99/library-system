package org.example;

//import org.example.DAO.BookDAO;
import org.example.Entity.Author;
import org.example.Entity.Book;
import org.example.repository.BookRepository;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.List;


public class Main {
    public static void main(String[] args) {

// Demo of N+1 problem
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            List<Author> authors = session.createQuery("from Author", Author.class).list();
//            for (Author au : authors) {
//                System.out.println(au.getName() + " -> " + au.getBooks().size());
//            }
//        }

//Solution for N+1 problem
        try (Session session = HibernateUtil.getSession().openSession()) {
            List<Author> authors = session.createQuery("select distinct a from Author a join fetch a.books", Author.class).list();
            for (Author au : authors) {
                System.out.println(au.getName() + " -> " + au.getBooks().size());
            }
        }


    }
}
package org.example.repository;

import org.example.Entity.Book;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookRepository {

    public void save(Book book){
        try(Session session =  HibernateUtil.getSession().openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(book);
            tx.commit();
        }
    }

    public Book findById(int id){
         try(Session session =  HibernateUtil.getSession().openSession()) {
        Transaction tx = session.beginTransaction();
        return  session.get(Book.class,id);
        }
        }

        public List<Book> findAll(){
            try(Session session =  HibernateUtil.getSession().openSession()) {
                Transaction tx = session.beginTransaction();
                return session.createQuery("From Book",Book.class).list();
            }
            }

            public void updateBook(Book book){
                try(Session session =  HibernateUtil.getSession().openSession()) {
                    Transaction tx = session.beginTransaction();
                    session.merge(Book.class) ;
                    tx.commit();
                }

            }

            public void deleteById(int id){
               Book book= new Book();
                try(Session session =  HibernateUtil.getSession().openSession()) {
                    Transaction tx = session.beginTransaction();
                   book= session.get(Book.class,id);
                   session.remove(book);
                   tx.commit();
                }

            }

}

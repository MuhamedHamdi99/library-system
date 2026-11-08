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
                    session.merge(book) ;
                    tx.commit();
                }

            }

    public boolean deleteById(int id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction tx = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book == null) {
                tx.rollback();
                return false;
            }
            session.remove(book);
            tx.commit();
            return true;
        }
    }

}

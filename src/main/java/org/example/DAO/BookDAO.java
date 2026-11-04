package org.example.DAO;

import org.example.Entity.Book;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookDAO {


    private Connection GetConnection() throws SQLException {
        try(InputStream input = BookDAO.class.getClassLoader().getResourceAsStream("db.properties")){

            Properties prop = new Properties();
            prop.load(input);
            return DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.username"),
                    prop.getProperty("db.password")
            );

        } catch (IOException e) {
            throw new RuntimeException("Can't read properties file : ",e);
        }
    }

    public void insertBook(Book book){
        String sql = "Insert Into Books( Title, Author, Price) values(?,?,?)";

        try(Connection conn =GetConnection()) {
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, book.getTitle());
            prep.setString(2, book.getAuthor());
            prep.setBigDecimal(3,book.getPrice());
            prep.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Book> findAll(){
        String sql = "Select Id, Title, Author, Price From Books";
        List<Book> books = new ArrayList<>();

        try(Connection connection = GetConnection()) {
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                Book b = new Book();
                b.setId(rs.getInt("Id"));
                b.setTitle(rs.getString("Title"));
                b.setAuthor(rs.getString("Author"));
                b.setPrice(rs.getBigDecimal("Price"));
                books.add(b);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }


    public Book findById(int id){
        Book book1 = new Book();

        try (Connection conn = GetConnection()){
            String sql = "Select Id, Title, Author, Price From Books where Id = ? ";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1,id);

            try(ResultSet rs = prep.executeQuery()) {
                  if(rs.next()){
                        book1.setId(rs.getInt("Id"));
                        book1.setTitle(rs.getString("Title"));
                        book1.setAuthor(rs.getString("Author"));
                        book1.setPrice(rs.getBigDecimal("Price"));
                        return book1;
                            }
                        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public boolean deleteById(int id){
        String sql = "Delete From Books where Id = ? ";
        try (Connection conn = GetConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1,id);
            int row =prep.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean updateBook(Book book){
        String sql = "Update Books Set Title = ?,Author = ?,Price = ? Where Id = ?";
        try(Connection conn = GetConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1,book.getTitle());
            prep.setString(2,book.getAuthor());
            prep.setBigDecimal(3,book.getPrice());
            prep.setInt(4,book.getId());

            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

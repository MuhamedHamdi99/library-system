package org.example.DAO;

import org.example.Entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
   private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;encrypt=true;trustServerCertificate=true";
    private static final String username = "sa";
    private static final String password = "sa";

    private Connection GetConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public void insertBook(Book book){
        String sql = "insert into Books(Title,Auther,Price) values(?,?,?)";

        try(Connection conn =GetConnection()) {
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, book.getTitle());
            prep.setString(2, book.getAuther());
            prep.setDouble(3,book.getPrice());
            prep.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Book> findAll(){
        String sql = "Select Id,Title,Auther,Price From Books";
        List<Book> books = new ArrayList<>();

        try(Connection connection = GetConnection()) {
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                Book b = new Book();
                b.setId(rs.getInt("Id"));
                b.setTitle(rs.getString("Title"));
                b.setAuther(rs.getString("Auther"));
                b.setPrice(rs.getDouble("Price"));
                books.add(b);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }


}

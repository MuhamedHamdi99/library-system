package org.example.DAO;

import org.example.Entity.Author;
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

    public void insertBook(Book book) {
        String sql = "INSERT INTO Books (Title, author_id, Price) VALUES (?, ?, ?)";

        try (Connection conn = GetConnection();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, book.getTitle());
            if (book.getAuthor() != null) {
                prep.setInt(2, book.getAuthor().getId());
            } else {
                prep.setNull(2, Types.INTEGER);
            }
            prep.setBigDecimal(3, book.getPrice());
            prep.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Book> findAll() {
        String sql = "SELECT b.Id, b.Title, b.Price, a.id AS author_id, a.name AS author_name " +
                "FROM Books b LEFT JOIN Author a ON b.author_id = a.id";
        List<Book> books = new ArrayList<>();

        try (Connection conn = GetConnection();
             PreparedStatement prep = conn.prepareStatement(sql);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("Id"));
                b.setTitle(rs.getString("Title"));
                b.setPrice(rs.getBigDecimal("Price"));

                int authorId = rs.getInt("author_id");
                if (!rs.wasNull()) {
                    Author a = new Author();
                    a.setId(authorId);
                    a.setName(rs.getString("author_name"));
                    b.setAuthor(a);
                }
                books.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public Book findById(int id) {
        String sql = "SELECT b.Id, b.Title, b.Price, a.id AS author_id, a.name AS author_name " +
                "FROM Books b LEFT JOIN Author a ON b.author_id = a.id " +
                "WHERE b.Id = ?";

        try (Connection conn = GetConnection();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setInt(1, id);

            try (ResultSet rs = prep.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("Id"));
                    book.setTitle(rs.getString("Title"));
                    book.setPrice(rs.getBigDecimal("Price"));

                    int authorId = rs.getInt("author_id");
                    if (!rs.wasNull()) {
                        Author a = new Author();
                        a.setId(authorId);
                        a.setName(rs.getString("author_name"));
                        book.setAuthor(a);
                    }
                    return book;
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

    public boolean updateBook(Book book) {
        String sql = "UPDATE Books SET Title = ?, author_id = ?, Price = ? WHERE Id = ?";

        try (Connection conn = GetConnection();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, book.getTitle());
            if (book.getAuthor() != null) {
                prep.setInt(2, book.getAuthor().getId());
            } else {
                prep.setNull(2, Types.INTEGER);
            }
            prep.setBigDecimal(3, book.getPrice());
            prep.setInt(4, book.getId());

            return prep.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

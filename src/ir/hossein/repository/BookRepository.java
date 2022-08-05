package ir.hossein.repository;

import ir.hossein.domain.Books;
import ir.hossein.util.Databaseutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private Connection connection;
    private Books books;
    private Books myBooks;
    List<Books> bookList = new ArrayList<>();
    List<Books> myBookList = new ArrayList<>();


    public Books createBook(String enteredBookName,String enteredAuthorName) throws SQLException {
        String query = "insert into library.book (name , status,author_name) values (? , ? ,? )";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, enteredBookName);
        preparedStatement.setBoolean(2 , true);
        preparedStatement.setString(3 , enteredAuthorName);
        preparedStatement.executeUpdate();
        return books;
    }
    public void deleteBook(Integer enteredBookIb) throws SQLException {
        String query = "delete from library.book where id=?";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,enteredBookIb);
        preparedStatement.executeUpdate();
    }

    public void makeBookStatusTrue(Integer enteredBookIb) throws SQLException {
        String query = "UPDATE book SET status = 1 WHERE id=?";
        connection=Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,enteredBookIb);
        preparedStatement.executeUpdate();
    }

    public void makeBookStatusFalse(Integer enteredBookIb) throws SQLException {
        String query = "UPDATE book SET status = 0 WHERE id=?";
        connection=Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,enteredBookIb);
        preparedStatement.executeUpdate();
    }

    public void makeOrderStatusFalse(Integer enteredUserId,Integer enteredBookId) throws SQLException {
        String query = "UPDATE user_book SET return_status = 0 WHERE user_id=? and book_id=? and status=1";
        connection=Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,enteredUserId);
        preparedStatement.setInt(2,enteredBookId);
        preparedStatement.executeUpdate();
    }

    public List<Books> findAllBooks() throws SQLException {
        String query = "select * from book where status=1";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            books = new Books(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getBoolean(3),resultSet.getString(4));
            bookList.add(books);
        }
        return bookList;
    }

    public List<Books> findAllMyBooks(Integer tempUserId) throws SQLException {
        String query = "select *  from library.book join  library.user_book on user_book.book_id=book.id where user_book.user_id=? and user_book.status=1 and user_book.return_status=1 ";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,tempUserId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            myBooks = new Books(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getBoolean(3),resultSet.getString(4));
            myBookList.add(myBooks);
        }
        return myBookList;
    }



//    public List<Books> findAllMyBooks() {
//        String query = "select *  from library.book join  library.orders on orders.book_id=book.id where orders.user_id=?;";
//        connection= Databaseutil.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//        return null;
//    }
}

package ir.hossein.repository;

import ir.hossein.domain.Order;
import ir.hossein.domain.User;
import ir.hossein.util.Databaseutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private Connection connection;
    private User user;
    private Order order;
    List<User> userList = new ArrayList<>();
    List<Order> userBookOrderList = new ArrayList<>();

    public User createUser(String enteredFirstName,String enteredSurName, String enteredUsername, String enteredPassword) throws SQLException {
        String query = "insert into library.user (firstName,surName,userName,password) value(? , ? , ? , ?)";
        connection = Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, enteredFirstName);
        preparedStatement.setString(2, enteredSurName);
        preparedStatement.setString(3, enteredUsername);
        preparedStatement.setString(4, enteredPassword);
        preparedStatement.executeUpdate();
        return user;
    }

    public void deleteUser(Integer enteredUserId) throws SQLException {
        String query = "delete from library.user where id=?";
        connection = Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, enteredUserId);
        preparedStatement.executeUpdate();
    }

    public List<Order> checkOrdersForUser(Integer enteredUserId) throws SQLException {
        String query = "select * from library.user_book where user_id=?";
        connection = Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, enteredUserId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            order = new Order(resultSet.getInt(1),resultSet.getInt(2),
                    resultSet.getInt(3),resultSet.getDate(4),resultSet.getBoolean(5),resultSet.getBoolean(6),resultSet.getInt(7));
            userBookOrderList.add(order);
        }
        return userBookOrderList;
    }

    public List<User> findAllUser() throws SQLException {
        String query = "select * from library.user";
        connection = Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            user = new User(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),resultSet.getString(5));
            userList.add(user);
        }
        return userList;
    }

    public User findByUsernameAndPassword(String enteredUsername,String enteredPassword) throws SQLException {
        String query = "select * from user where userName=? and password=? Limit 1";
        connection = Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,enteredUsername);
        preparedStatement.setString(2,enteredPassword);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            user = new User(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));

        }
//        System.out.println(resultSet.getInt(1));

        return user;
    }
}

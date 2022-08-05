package ir.hossein.repository;

import ir.hossein.domain.Order;
import ir.hossein.util.Databaseutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

public class OrderRepository {
    private Connection connection;
    private Order order;
    List<Order> orderList = new ArrayList<>();

    public Order createOrder(Integer enteredUserId, Integer enteredBookId, LocalDateTime now, boolean status,boolean returnStatus,Integer enteredTariffId) throws SQLException {
        String query = "insert into library.user_book (user_id,book_id,create_date,status,return_status,Tariff_id) value(? , ?, ?, ? , ? , ?)";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,enteredUserId);
        preparedStatement.setInt(2,enteredBookId);
        preparedStatement.setString(3,now.toString());
        preparedStatement.setBoolean(4,status);
        preparedStatement.setBoolean(5,returnStatus);
        preparedStatement.setInt(6,enteredTariffId);
        preparedStatement.executeUpdate();
        return order;
    }

    public void deleteOrder(Integer enteredUserId,Integer enteredBookId) throws SQLException {
        String query = "delete from library.user_book where user_id=? and book_id=?";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,enteredUserId);
        preparedStatement.setInt(2,enteredBookId);
        preparedStatement.executeUpdate();
    }

    public List<Order> findAllOrders() throws SQLException {
        String query = "select * from library.user_book";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            order = new Order(resultSet.getInt(1),resultSet.getInt(2),
                    resultSet.getInt(3),resultSet.getDate(4),resultSet.getBoolean(5),resultSet.getBoolean(6),resultSet.getInt(7));
            orderList.add(order);
        }
        return orderList;
    }
}

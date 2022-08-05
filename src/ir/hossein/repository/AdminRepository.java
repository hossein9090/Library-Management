package ir.hossein.repository;

import ir.hossein.domain.Admin;
import ir.hossein.domain.User;
import ir.hossein.util.Databaseutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    private Connection connection;
    private Admin admin;

    public Admin createAdmin() throws SQLException {
        String query = "insert into library.admin (firstName,surName , userName,password) values (? , ? , ?, ?)";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,admin.getFirstName());
        preparedStatement.setString(2,admin.getSurName());
        preparedStatement.setString(3,admin.getUserName());
        preparedStatement.setString(4,admin.getPassword());
        preparedStatement.executeUpdate();
        return admin;
    }
    public void deleteAdmin() throws SQLException {
        String query = "delete from library.admin where id=?";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,admin.getId());
        preparedStatement.executeUpdate();
    }

    public Admin findByUsernameAndPassword(String enteredAdminUsername, String enteredAdminPassword) throws SQLException {
        String query = "select * from admin where userName=? and password=? Limit 1";
        connection = Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,enteredAdminUsername);
        preparedStatement.setString(2,enteredAdminPassword);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            admin = new Admin(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));

        }
//        System.out.println(resultSet.getInt(1));

        return admin;
    }
}

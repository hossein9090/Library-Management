package ir.hossein.repository;

import ir.hossein.domain.Tariff;
import ir.hossein.util.Databaseutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TariffRepository {
    private Connection connection;
    private Tariff tariff;

    public Tariff createTariff(Integer enteredNewTariff, LocalDateTime now) throws SQLException {
        String query = "insert into library.tariff (amount,create_date) value( ? , ? )";
        connection= Databaseutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,enteredNewTariff);
        preparedStatement.setString(2,now.toString());
        preparedStatement.executeUpdate();
        return tariff;
    }
}

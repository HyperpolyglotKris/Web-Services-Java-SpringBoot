package com.a3.FruitMonthlyPriceMicroservice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FruitMonthlyPriceController {

    @Autowired
    private Environment environment;

    @GetMapping("/fruit-monthly-price/fruit/{fruit}/month/{month}")
    public FruitMonthlyPrice retrieveFruitMonthPrice(@PathVariable String fruit, @PathVariable String month)
            throws SQLException {

        FruitMonthlyPrice fruitPrice = new FruitMonthlyPrice(fruit, month);
        String port = environment.getProperty("local.server.port");
        fruitPrice.setEnvironment(port);

        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "FRUIT_MONTHLY_PRICE", null);
        if (!tables.next()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE FRUIT_MONTHLY_PRICE AS SELECT * FROM CSVREAD('FMP.csv');");
        }

        Statement searchResultStatement = connection.createStatement();
        ResultSet resultSet = searchResultStatement
                .executeQuery("SELECT * FROM FRUIT_MONTHLY_PRICE WHERE FRUIT='" + fruit + "';");

        String result = "";
        while (resultSet.next()) {
            result = resultSet.getString(month);
        }

        fruitPrice.setPrice(Double.parseDouble(result));

        return fruitPrice;
    }
}

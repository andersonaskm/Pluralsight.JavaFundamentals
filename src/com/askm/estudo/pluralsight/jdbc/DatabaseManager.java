package com.askm.estudo.pluralsight.jdbc;

import org.apache.log4j.Logger;


import java.sql.*;
import java.util.*;

public class DatabaseManager {

    private static final Logger logger = Logger.getLogger(DatabaseManager.class);

    private static String getConnStr() {
        StringBuilder sbConn = new StringBuilder();

        String jDBCDriver = "jdbc:mysql://pluralsight.cxnon7fzkozl.us-west-2.rds.amazonaws.com";
        String database = "classicmodels";
        String username = "admin";
        String password = "XZsawq2112qwasZX";
        String timezone = "UTC";
        String jDBCParams = String.format("/%s?user=%s&password=%s&serverTimezone=%s"
                ,database,username, password, timezone);
        sbConn.append(jDBCDriver);
        sbConn.append(jDBCParams);
        return sbConn.toString();
    }

    public static ArrayList<Product> getProducts() throws Exception {

        String sbConn = getConnStr();
        String query = "SELECT * FROM products";
        ArrayList<Product> products = new ArrayList<Product>();

        try(Connection connection = DriverManager.getConnection(sbConn);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();) {

            fillArrayList(resultSet, products);

        } catch (Exception exception) {
            handleException(exception);
        }

        return products;
    }

    public static ArrayList<Product> getProductsByPriceRange(Double minValue, Double maxValue) throws Exception {

        String sbConn = getConnStr();
        String query = "SELECT * FROM products WHERE buyPrice BETWEEN ? AND ?";
        ArrayList<Product> products = new ArrayList<Product>();

        try(Connection connection = DriverManager.getConnection(sbConn);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {

            preparedStatement.setDouble(1, minValue);
            preparedStatement.setDouble(2, maxValue);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                fillArrayList(resultSet, products);
            }
        } catch (Exception exception) {
            handleException(exception);
        }

        return products;
    }

    public static Integer increasePriceBy(Double incrementPrice) throws Exception {

        String sbConn = getConnStr();
        String query = "UPDATE products SET buyPrice = (buyPrice + ?)";

        try(Connection connection = DriverManager.getConnection(sbConn);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {

            preparedStatement.setDouble(1, incrementPrice);
            return preparedStatement.executeUpdate();

        }
    }

    public static Integer decreasePriceBy(Double decrementPrice) throws Exception {

        String sbConn = getConnStr();
        String query = "UPDATE products SET buyPrice = (buyPrice - ?)";

        try(Connection connection = DriverManager.getConnection(sbConn);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {

            preparedStatement.setDouble(1, decrementPrice);
            //preparedStatement.setCharacterStream(1, InputStreamReader);
            return preparedStatement.executeUpdate();

        }
    }


    private static void fillArrayList(ResultSet resultSet, ArrayList<Product> products) throws SQLException {

        while(resultSet.next()){

            Product product = new Product();
            product.ProductCode = resultSet.getString("productCode");
            product.ProductLine = resultSet.getString("productLine");
            product.ProductName = resultSet.getString("productName");
            product.BuyPrice = resultSet.getDouble("buyPrice");
            //Reader clobContent = resultSet.getCharacterStream(1);
            //InputStream blobContent resultSet.getBinaryStream(1);

            products.add(product);
        }
    }

    private static void handleException(Exception exception) {
        logger.error("Erro: "+ exception.getMessage(), exception);
        if(exception instanceof SQLException) {
            SQLException sqlException = (SQLException) exception;
            logger.error("Error Code:" + sqlException.getErrorCode());
            logger.error("SQL State:" + sqlException.getSQLState());
        }
    }

}

package com.askm.estudo.pluralsight;

import com.askm.estudo.pluralsight.httpclient.CorredorOnibus;
import com.askm.estudo.pluralsight.httpclient.HttpClientExample;
import com.askm.estudo.pluralsight.jdbc.DatabaseManager;
import com.askm.estudo.pluralsight.jdbc.Product;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Main {

    private static final Logger logger = Logger.getRootLogger();
    /**
     * Main entry point for the program.
     * @param args program entry point arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, Exception {

        String programName = "Pluralsight Java Fundamentals.";
        logger.debug(programName);

        //TestHttpClient();
        TestJdbc();

    }

    private static void TestJdbc() throws Exception {

        ArrayList<Product> products;

        logDebugTitle("JDBC Get Products by Price Range");
        products = DatabaseManager.getProductsByPriceRange(30d, 40d);
        for (Product product: products) {
            logger.debug(product.getDetails());
        }

        logDebugTitle("JDBC Get Products");
        products = DatabaseManager.getProducts();
        for (Product product: products) {
            logger.debug(product.getDetails());
        }

        logDebugTitle("Increase price by 10");
        Integer increasedQty = DatabaseManager.increasePriceBy(10d);
        logger.debug(String.format("%s products has increased price.", increasedQty));

        logDebugTitle("JDBC Get Products");
        products = DatabaseManager.getProducts();
        for (Product product: products) {
            logger.debug(product.getDetails());
        }

        logDebugTitle("Decrease price by 10");
        Integer decreasedQty = DatabaseManager.decreasePriceBy(10d);
        logger.debug(String.format("%s products has decreased price.", decreasedQty));

        logDebugTitle("JDBC Get Products");
        products = DatabaseManager.getProducts();
        for (Product product: products) {
            logger.debug(product.getDetails());
        }
    }

    private static void logDebugTitle(String message){
        logger.debug("----------------------------------------");
        logger.debug(message);
        logger.debug("----------------------------------------");
    }

    private static void TestHttpClient() throws IOException, InterruptedException, ExecutionException {
        logger.debug("Olho Vivo login");
        String login = HttpClientExample.olhoVivoLogin();
        logger.debug(String.format("Result is: %s",login));

        logger.debug("Olho Vivo Corredores");
        CorredorOnibus[] corredoresOnibus = HttpClientExample.olhoVivoCorredores();
        for (CorredorOnibus corredor: corredoresOnibus) {
            logger.debug(corredor.getDetails());
        }

        logger.debug("WebSocket Test");
        HttpClientExample.getWebSocketMessages(3);
    }


}

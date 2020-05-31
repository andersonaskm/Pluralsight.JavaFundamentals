package com.askm.estudo.pluralsight;

import com.askm.estudo.pluralsight.httpclient.HttpClientExample;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {

    private static final Logger logger = Logger.getRootLogger();
    /**
     * Main entry point for the program.
     * @param args program entry point arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        String programName = "Pluralsight Java Fundamentals.";
        logger.debug(programName);

        logger.debug("API Free Exchange Rate");
        String exchangeRates = HttpClientExample.getFreeExchangeRate();
        logger.debug(String.format("Result is: %s",exchangeRates));

        logger.debug("WebSocket Test");
        HttpClientExample.getWebSocketMessages(3);

    }


}

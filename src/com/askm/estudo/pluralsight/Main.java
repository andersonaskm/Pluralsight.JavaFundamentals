package com.askm.estudo.pluralsight;

import org.apache.log4j.Logger;

public class Main {

    private static final Logger logger = Logger.getRootLogger();
    /**
     * Main entry point for the program.
     * @param args program entry point arguments
     */
    public static void main(String[] args) {

        String programName = "Pluralsight Java Fundamentals.";
        logger.debug(programName);

        testNumbersTypes();
        testCharBoolTypes();

    }

    private static void testNumbersTypes() {
        byte byteNumber= 120;
        logger.info(byteNumber);

        short shortNumber = 32000;
        logger.info(shortNumber);

        int intNumber = 21334434;
        logger.info(intNumber);

        long longNumber = 45354534L;
        logger.info(longNumber);

        float floatNumber = 10.4f;
        logger.info(floatNumber);

        double doubleNumber = 23.45d;
        logger.info(doubleNumber);
    }

    private static void testCharBoolTypes() {
        char singleChar = '\u00DA';
        logger.info(singleChar);

        logger.info(false);
    }
}

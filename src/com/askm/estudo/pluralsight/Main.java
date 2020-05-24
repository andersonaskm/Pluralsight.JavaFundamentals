package com.askm.estudo.pluralsight;

import org.apache.log4j.Logger;

public class Main {


    /**
     * Main entry point for the program.
     * @param args program entry point arguments
     */
    public static void main(String[] args) {

        Logger logger = Logger.getLogger(com.askm.estudo.pluralsight.Main.class);

        String programName = "Pluralsight Java Fundamentals.";
        logger.debug(programName);

    }
}

package dev.rusyaev;

import dev.rusyaev.entity.Address;
import dev.rusyaev.utils.DecoratorCollectionForStatisticsAddresses;
import dev.rusyaev.utils.Extension;
import dev.rusyaev.utils.parsing.CSVParserAddress;
import dev.rusyaev.utils.parsing.StrategyParser;
import dev.rusyaev.utils.parsing.XMLParserAddress;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException ignore) {
            System.out.println("Logger settings not found!");
        } finally {
            logger.fine("Program is starting.");
        }

        try(Scanner scanner = new Scanner(System.in)) {
            String filePath;

            System.out.println("Enter the path to the file with the extension 'cvs' or 'xml'. To exit the program enter 'exit'.");
            while (!(filePath = scanner.nextLine()).equalsIgnoreCase("exit")) {
                DecoratorCollectionForStatisticsAddresses<Address> addresses = new DecoratorCollectionForStatisticsAddresses<>(new HashSet<>(), new HashMap<>(), new HashMap<>());

                long start = System.currentTimeMillis();

                String extension = Extension.getExtension(filePath);
                try {
                    logger.fine("Parsing  is starting");
                    switch (extension) {
                        case "csv" -> new StrategyParser(new CSVParserAddress()).parsing(filePath, addresses);
                        case "xml" -> new StrategyParser(new XMLParserAddress()).parsing(filePath, addresses);
                        default -> {
                            logger.fine("Format incorrect, try again! Format: " + extension + ". Path: " + filePath);
                            System.out.println("Format incorrect, try again!");
                            continue;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("The file was not found, check the correctness of the entered data.");
                    continue;
                }

                System.out.println("\n______________________________________________");
                System.out.println("Number of apartments on each floor of each city:\n");
                for (String city : addresses.getCountAddressesOnFloor().keySet()) {
                    System.out.println("\nCity: " + city);
                    for (Map.Entry<Byte, Long> entry : addresses.getCountAddressesOnFloor().get(city).entrySet()) {
                        System.out.printf("On the %d floor there are %d addresses\n", entry.getKey(), entry.getValue());
                    }
                }
                System.out.println("______________________________________________\n");

                System.out.println("\n______________________________________________");
                System.out.println("List of duplicate addresses:\n");
                for (Map.Entry<Address, Integer> address : addresses.getDoubleAddresses().entrySet()) {
                    System.out.println(address.getKey() + " count: " + address.getValue());
                }
                System.out.println("______________________________________________\n");

                long end = System.currentTimeMillis();
                logger.fine("Parsing  is completed in " + (end - start) + " ms.");

                System.out.printf("\nSuccess! Created %d addresses.\n", addresses.size());
                System.out.println("To exit the program enter 'exit' or enter the path to the file.");
            }
        }
        logger.fine("Program is completed without problem.");
    }

}

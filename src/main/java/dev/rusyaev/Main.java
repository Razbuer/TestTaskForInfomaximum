package dev.rusyaev;

import dev.rusyaev.entity.Address;
import dev.rusyaev.utils.DecoratorCollectionForStatisticsAddresses;
import dev.rusyaev.utils.Extension;
import dev.rusyaev.utils.parsing.CSVParserAddress;
import dev.rusyaev.utils.parsing.StrategyParser;
import dev.rusyaev.utils.parsing.XMLParserAddress;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            String filePath;

            System.out.println("Enter the path to the file with the extension 'cvs' or 'xml'. To exit the program enter 'exit'.");
            while (!(filePath = scanner.nextLine()).equalsIgnoreCase("exit")) {
                DecoratorCollectionForStatisticsAddresses<Address> addresses = new DecoratorCollectionForStatisticsAddresses<>(new HashSet<>(), new HashMap<>(), new HashMap<>());

                long start = System.currentTimeMillis();

                String extension = Extension.getExtension(filePath);
                try {
                    switch (extension) {
                        case "csv" -> new StrategyParser(new CSVParserAddress()).parsing(filePath, addresses);
                        case "xml" -> new StrategyParser(new XMLParserAddress()).parsing(filePath, addresses);
                        default -> {
                            System.out.println("Format incorrect, try again!");
                            continue;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("The file was not found, check the correctness of the entered data.");
                    continue;
                }

                long end = System.currentTimeMillis();

                System.out.printf("Working hours of the program: %dms.", (end - start));

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

                long end2 = System.currentTimeMillis();
                System.out.printf("Working hours of the program: %dms.", (end2 - start));

                System.out.printf("\nSuccess! Created %d addresses.\n", addresses.size());
                System.out.println("To exit the program enter 'exit' or enter the path to the file.");
            }
        }
    }

}

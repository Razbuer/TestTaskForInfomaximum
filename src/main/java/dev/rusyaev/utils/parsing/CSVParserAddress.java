package dev.rusyaev.utils.parsing;

import dev.rusyaev.config.Config;
import dev.rusyaev.entity.Address;

import java.io.*;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVParserAddress implements ParserAddress {
    private static final Logger logger = Logger.getLogger(CSVParserAddress.class.getName());

    @Override
    public void parsing(String filePath, Collection<Address> collection) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            while (reader.ready()) {
                try {
                    String fullAddress = reader.readLine();

                    collection.add(getAddress(fullAddress));
                } catch (Exception ignoreBecauseIncorrectData) {}
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new FileNotFoundException();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private Address getAddress(String parseLine) {
        String[] partsOfAddress = parseLine.split(Config.getProperty("separatorCSV"));

        String city = partsOfAddress[0].split("\"")[1].trim();
        String street = partsOfAddress[1].split("\"")[1].trim();
        short house = Short.parseShort(partsOfAddress[2].trim());
        byte floor = Byte.parseByte(partsOfAddress[3].trim());

        return new Address(city, street, house, floor);
    }
}

package dev.rusyaev.utils.parsing;

import dev.rusyaev.config.Config;
import dev.rusyaev.entity.Address;

import java.io.*;
import java.util.Collection;

public class CSVParserAddress implements ParserAddress {
    @Override
    public void parsing(String filePath, Collection<Address> collection) throws FileNotFoundException  {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                try {
                    String fullAddress = reader.readLine();

                    collection.add(getAddress(fullAddress));
                } catch (Exception ignoreBecauseIncorrectData) {}
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Address getAddress(String parseLine) {
        String[] partsOfAddress = parseLine.split(Config.getProperty("separatorCSV"));

        String city = partsOfAddress[0].split("\"")[1].trim();
        String street = partsOfAddress[1].split("\"")[1].trim();
        short house = Short.parseShort(partsOfAddress[2].trim());
        byte floor = Byte.parseByte(partsOfAddress[3].trim());

        return new Address(city, street, house, floor);
    }
}

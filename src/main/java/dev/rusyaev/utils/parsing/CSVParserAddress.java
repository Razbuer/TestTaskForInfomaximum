package dev.rusyaev.utils.parsing;

import dev.rusyaev.config.Config;
import dev.rusyaev.entity.Address;

public class CSVParserAddress extends AbstractParserAddress implements ParserAddress {
    @Override
    public Address getAddress(String parseLine) {
        String[] partsOfAddress = parseLine.split(Config.getProperty("separatorCSV"));

        String city = partsOfAddress[0].split("\"")[1].trim();
        String street = partsOfAddress[1].split("\"")[1].trim();
        short house = Short.parseShort(partsOfAddress[2].trim());
        byte floor = Byte.parseByte(partsOfAddress[3].trim());

        return new Address(city, street, house, floor);
    }
}

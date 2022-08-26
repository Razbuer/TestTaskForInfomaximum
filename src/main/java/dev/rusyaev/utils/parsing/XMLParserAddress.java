package dev.rusyaev.utils.parsing;

import dev.rusyaev.entity.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class XMLParserAddress implements ParserAddress {
    @Override
    public void parsing(String filePath, Collection<Address> collection) throws FileNotFoundException {
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
            throw new RuntimeException(e);
        }
    }

    public Address getAddress(String parseLine) {
        //parseLine = parseLine.replaceAll("\\s*<item","").replaceAll("/>\\s*", "").replaceAll("\\s*=\\s*", "=");

        StringBuilder fullAddress = new StringBuilder(parseLine);
        replace(fullAddress, "<item", "");
        replace(fullAddress, "/>", "");

        String[] partsOfAddress = fullAddress.toString().split("\"");
        Map<String, String> mapPartsOfAddress = new HashMap<>();
        for (int i = 0; i < partsOfAddress.length; i++) {
            if (partsOfAddress.length > (i + 1))
                mapPartsOfAddress.put(partsOfAddress[i].replace("=", "").trim(), partsOfAddress[++i].trim());
        }

        String city = mapPartsOfAddress.get("city");
        String street = mapPartsOfAddress.get("street");
        short house = Short.parseShort(mapPartsOfAddress.get("house"));
        byte floor = Byte.parseByte(mapPartsOfAddress.get("floor"));

        return new Address(city, street, house, floor);
    }

    private void replace(StringBuilder str, String from, String to) {
        str.replace(str.indexOf(from), str.indexOf(from) + from.length(), to);
    }
}

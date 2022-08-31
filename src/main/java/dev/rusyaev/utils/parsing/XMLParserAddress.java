package dev.rusyaev.utils.parsing;

import dev.rusyaev.entity.Address;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLParserAddress implements ParserAddress {
    private static final Logger logger = Logger.getLogger(XMLParserAddress.class.getName());

    @Override
    public void parsing(String filePath, Collection<Address> collection) throws FileNotFoundException {
        try {
            XMLStreamReader reader = XMLInputFactory.newDefaultFactory().createXMLStreamReader(new InputStreamReader(new FileInputStream(filePath)));

            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("item")) {
                    String city = null;
                    String street = null;
                    short house = 0;
                    byte floor = 0;

                    for (int i = 0; i < reader.getAttributeCount(); i++) {
                        switch (reader.getAttributeLocalName(i)) {
                            case "city" -> city = reader.getAttributeValue(i);
                            case "street" -> street = reader.getAttributeValue(i);
                            case "house" -> house = Short.parseShort(reader.getAttributeValue(i));
                            case "floor" -> floor = Byte.parseByte(reader.getAttributeValue(i));
                        }
                    }

                    Address address = new Address(city, street, house, floor);

                    collection.add(address);
                }
            }
        } catch (XMLStreamException e) {
            logger.log(Level.INFO, e.getMessage());
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, e.getMessage());
            throw new FileNotFoundException();
        }
    }
}

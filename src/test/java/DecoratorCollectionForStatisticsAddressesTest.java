import dev.rusyaev.entity.Address;
import dev.rusyaev.utils.DecoratorCollectionForStatisticsAddresses;
import dev.rusyaev.utils.parsing.CSVParserAddress;
import dev.rusyaev.utils.parsing.StrategyParser;
import dev.rusyaev.utils.parsing.XMLParserAddress;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorCollectionForStatisticsAddressesTest {
    private DecoratorCollectionForStatisticsAddresses<Address> addresses;
    private final Path pathToCSV = Paths.get("src/test/resources/address.csv");
    private final Path pathToXML = Paths.get("src/test/resources/address.xml");
    private static final Map<String, Map<Byte, Long>> countAddressesOnFloorStandard = new HashMap<>();
    private static final Map<Address, Integer> doubleAddressesStandard = new HashMap<>();;

    @BeforeAll
    public static void init() {
        doubleAddressesStandard.put(new Address("Балахна","4-я Майская улица", (short) 55, (byte) 2), 2);
        doubleAddressesStandard.put(new Address("Брянск","Железнодорожная улица", (short) 32, (byte) 1), 2);
        doubleAddressesStandard.put(new Address("Брянск","Текстильщиков, улица", (short) 14, (byte) 2), 2);
        doubleAddressesStandard.put(new Address("Брянск","2-я Пионерская, улица", (short) 128, (byte) 2), 2);
        doubleAddressesStandard.put(new Address("Брянск","Симферопольское шоссе", (short) 147, (byte) 5), 2);

        countAddressesOnFloorStandard.put("Балахна", new HashMap<>());
        countAddressesOnFloorStandard.get("Балахна").put((byte)2, 3L);
        countAddressesOnFloorStandard.put("Брянск", new HashMap<>());
        countAddressesOnFloorStandard.get("Брянск").put((byte)1, 3L);
        countAddressesOnFloorStandard.get("Брянск").put((byte)2, 5L);
        countAddressesOnFloorStandard.get("Брянск").put((byte)5, 3L);
        countAddressesOnFloorStandard.put("Асбест", new HashMap<>());
        countAddressesOnFloorStandard.get("Асбест").put((byte)2, 2L);
    }

    @BeforeEach
    public void initBeforeEach() {
        addresses = new DecoratorCollectionForStatisticsAddresses<>(new HashSet<>(), new HashMap<>(), new HashMap<>());
    }

    @Test
    public void parseCSVShouldBeThrowFNFException() {
        assertThrows(FileNotFoundException.class, () -> new StrategyParser(new CSVParserAddress()).parsing("test", addresses));
    }

    @Test
    public void parseXMLShouldBeThrowFNFException() {
        assertThrows(FileNotFoundException.class, () -> new StrategyParser(new XMLParserAddress()).parsing("test", addresses));
    }

    @Test
    public void parseCSVShouldBeReturnEqualsListDoubleAddresses() throws FileNotFoundException {
        new StrategyParser(new CSVParserAddress()).parsing(pathToCSV, addresses);
        assertTrue(doubleAddressesStandard.entrySet().containsAll(addresses.getDoubleAddresses().entrySet()));
    }

    @Test
    public void parseXMLShouldBeReturnEqualsListDoubleAddresses() throws FileNotFoundException {
        new StrategyParser(new XMLParserAddress()).parsing(pathToXML, addresses);
        assertTrue(doubleAddressesStandard.entrySet().containsAll(addresses.getDoubleAddresses().entrySet()));
    }

    @Test
    public void parseCSVShouldBeReturnEqualsListCountAddressesOnFloor() throws FileNotFoundException {
        new StrategyParser(new CSVParserAddress()).parsing(pathToCSV, addresses);
        assertTrue(countAddressesOnFloorStandard.entrySet().containsAll(addresses.getCountAddressesOnFloor().entrySet()));
    }

    @Test
    public void parseXMLShouldBeReturnEqualsListCountAddressesOnFloor() throws FileNotFoundException {
        new StrategyParser(new XMLParserAddress()).parsing(pathToXML, addresses);
        assertTrue(countAddressesOnFloorStandard.entrySet().containsAll(addresses.getCountAddressesOnFloor().entrySet()));
    }
}

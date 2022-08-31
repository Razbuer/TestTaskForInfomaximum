import dev.rusyaev.entity.Address;
import dev.rusyaev.utils.parsing.CSVParserAddress;
import dev.rusyaev.utils.parsing.StrategyParser;
import dev.rusyaev.utils.parsing.XMLParserAddress;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVParserAddressTest {
    private final List<Address> standardAddresses = List.of(new Address("Асбест", "4-я Майская улица", (short) 55, (byte) 2),
                                                    new Address("Балахна", "4-я Майская улица", (short) 55, (byte) 2),
                                                    new Address("Брянск", "Железнодорожная улица", (short) 32, (byte) 1),
                                                    new Address("Брянск", "Текстильщиков, улица", (short) 14, (byte) 2),
                                                    new Address("Брянск", "2-я Пионерская, улица", (short) 128, (byte) 2),
                                                    new Address("Брянск", "Симферопольское шоссе", (short) 147, (byte) 5),
                                                    new Address("Балахна", "4-я Майская улица", (short) 55, (byte) 2),
                                                    new Address("Брянск", "Железнодорожная улица", (short) 32, (byte) 1),
                                                    new Address("Брянск", "Текстильщиков, улица", (short) 14, (byte) 2),
                                                    new Address("Брянск", "2-я Пионерская, улица", (short) 128, (byte) 2),
                                                    new Address("Брянск", "Симферопольское шоссе", (short) 147, (byte) 5));
    private final List<Address> testAddresses = new ArrayList<>();
    private final Path pathToCSV = Paths.get("src/test/resources/address.csv");

    @Test
    public void parsingShouldBeReturnStandardListAddresses() throws FileNotFoundException {
        new StrategyParser(new CSVParserAddress()).parsing(pathToCSV, testAddresses);
        assertEquals(standardAddresses, testAddresses);
    }
}

import dev.rusyaev.entity.Address;
import dev.rusyaev.utils.parsing.CSVParserAddress;
import dev.rusyaev.utils.parsing.StrategyParser;
import dev.rusyaev.utils.parsing.XMLParserAddress;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class XMLParserAddressTest {
    private final List<Address> standardAddresses = List.of(new Address("������", "4-� ������� �����", (short) 55, (byte) 2),
            new Address("�������", "4-� ������� �����", (short) 55, (byte) 2),
            new Address("������", "��������������� �����", (short) 32, (byte) 1),
            new Address("������", "�������������, �����", (short) 14, (byte) 2),
            new Address("������", "2-� ����������, �����", (short) 128, (byte) 2),
            new Address("������", "��������������� �����", (short) 147, (byte) 5),
            new Address("�������", "4-� ������� �����", (short) 55, (byte) 2),
            new Address("������", "��������������� �����", (short) 32, (byte) 1),
            new Address("������", "�������������, �����", (short) 14, (byte) 2),
            new Address("������", "2-� ����������, �����", (short) 128, (byte) 2),
            new Address("������", "��������������� �����", (short) 147, (byte) 5));
    private final List<Address> testAddresses = new ArrayList<>();
    private final Path pathToXML = Paths.get("src/test/resources/address.xml");

    @Test
    public void getAddressShouldBeReturnStandardAddress() throws FileNotFoundException {
        new StrategyParser(new XMLParserAddress()).parsing(pathToXML, testAddresses);
        assertEquals(standardAddresses, testAddresses);
    }

}

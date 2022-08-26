import dev.rusyaev.entity.Address;
import dev.rusyaev.utils.parsing.XMLParserAddress;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class XMLParserAddressTest {
    private final Address standardAddress = new Address("Some city", "Some street", (short) 1, (byte) 1);

    @ParameterizedTest
    @ValueSource(strings = {"<item city=\"Some city\" street=\"Some street\" house=\"1\" floor=\"1\" />",
                            "<item house=\"1\" street=\"Some street\" city=\"Some city\" floor=\"1\" />",
                            "    <item     city=\"Some city   \"    street=\"  Some street\"     house=\"1\"    floor=\"  01 \"     />    "})
    public void getAddressShouldBeReturnStandardAddress(String parseLine) {
        assertEquals(standardAddress, new XMLParserAddress().getAddress(parseLine));
    }

}

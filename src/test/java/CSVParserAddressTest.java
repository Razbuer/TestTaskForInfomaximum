import dev.rusyaev.entity.Address;
import dev.rusyaev.utils.parsing.CSVParserAddress;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CSVParserAddressTest {
    private final Address standardAddress = new Address("Some city", "Some street", (short) 1, (byte) 1);

    @ParameterizedTest
    @ValueSource(strings = {"\"Some city\";\"Some street\";1;1", "   \"    Some city    \";\" Some street\"    ;  1  ;   01 "})
    public void getAddressShouldBeReturnStandardAddress(String parseLine) {
        assertEquals(standardAddress, new CSVParserAddress().getAddress(parseLine));
    }
}

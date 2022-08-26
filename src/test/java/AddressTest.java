import dev.rusyaev.entity.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {
    private final Address x = new Address("Some city", "Some street", (short) 1, (byte) 1);
    private final Address y = new Address("Some city", "Some street", (short) 1, (byte) 1);
    private final Address z = new Address("Some city", "Some street", (short) 1, (byte) 1);

    @Test
    public void equalsShouldBeReflexive() {
        assertTrue(x.equals(x));
    }

    @Test
    public void equalsShouldBeSymmetric() {
        assertTrue(x.equals(y));
        assertTrue(y.equals(x));
    }

    @Test
    public void equalsShouldBeTransitive() {
        assertTrue(x.equals(y));
        assertTrue(y.equals(z));
        assertTrue(x.equals(z));
    }

    @Test
    public void equalsShouldBeConsistent() {
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
        assertTrue(x.equals(y));
    }

    @Test
    public void equalsShouldBeReturnFalseForNull() {
        assertFalse(x.equals(null));
    }

    @Test
    public void hashCodeShouldReturnTheSameInteger() {
        int hashCode = x.hashCode();
        assertEquals(hashCode, x.hashCode());
        assertEquals(hashCode, x.hashCode());
        assertEquals(hashCode, x.hashCode());
    }

    @Test
    public void hashCodeShouldBeReturnSameIntegerForTwoEqualsObjects() {
        if (x.equals(y))
            assertEquals(x.hashCode(), y.hashCode());
        else
            assertNotEquals(x.hashCode(), y.hashCode());
    }
}

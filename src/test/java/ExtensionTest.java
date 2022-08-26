import dev.rusyaev.utils.Extension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtensionTest {
    @ParameterizedTest
    @ValueSource(strings = {"file.csv", "FILE.CSV", "C:/temp/file.csv", "C:/temp/temp/temp/temp/temp/temp/temp/temp/file.csv"})
    public void methodShouldBeReturnCSVExtensionToLowerCaseFromString(String pathToFile) {
        assertEquals("csv", Extension.getExtension(pathToFile));
    }

    @ParameterizedTest
    @ValueSource(strings = {"file.csv", "FILE.CSV", "C:/temp/file.csv", "C:/temp/temp/temp/temp/temp/temp/temp/temp/file.csv"})
    public void methodShouldBeReturnCSVExtensionToLowerCaseFromPath(String pathToFile) {
        Path path = Paths.get(pathToFile);
        assertEquals("csv", Extension.getExtension(path));
    }

    @ParameterizedTest
    @ValueSource(strings = {"file.xml", "FILE.XML", "C:/temp/file.xml", "C:/temp/temp/temp/temp/temp/temp/temp/temp/file.xml"})
    public void methodShouldBeReturnXMLExtensionToLowerCaseFromString(String pathToFile) {
        assertEquals("xml", Extension.getExtension(pathToFile));
    }

    @ParameterizedTest
    @ValueSource(strings = {"file.xml", "FILE.XML", "C:/temp/file.xml", "C:/temp/temp/temp/temp/temp/temp/temp/temp/file.xml"})
    public void methodShouldBeReturnXMLExtensionToLowerCaseFromPath(String pathToFile) {
        Path path = Paths.get(pathToFile);
        assertEquals("xml", Extension.getExtension(path));
    }
}

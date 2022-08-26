package dev.rusyaev.utils.parsing;

import dev.rusyaev.entity.Address;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Collection;

public class StrategyParser {
    ParserAddress parserAddress;

    public StrategyParser(ParserAddress parserAddress) {
        this.parserAddress = parserAddress;
    }

    public void parsing(String pathToFile, Collection<Address> collection) throws FileNotFoundException {
        doParsing(pathToFile, collection);
    }

    public void parsing(Path pathToFile, Collection<Address> collection) throws FileNotFoundException {
        doParsing(String.valueOf(pathToFile.toAbsolutePath()), collection);
    }

    private void doParsing(String pathToFile, Collection<Address> collection) throws FileNotFoundException {
        parserAddress.parsing(pathToFile, collection);
    }

    public void setStrategyParse(ParserAddress parserAddress) {
        this.parserAddress = parserAddress;
    }
}

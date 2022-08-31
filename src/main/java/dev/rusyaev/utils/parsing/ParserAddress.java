package dev.rusyaev.utils.parsing;

import dev.rusyaev.entity.Address;

import java.io.FileNotFoundException;
import java.util.Collection;

public interface ParserAddress {
    void parsing(String filePath, Collection<Address> collection) throws FileNotFoundException;
}

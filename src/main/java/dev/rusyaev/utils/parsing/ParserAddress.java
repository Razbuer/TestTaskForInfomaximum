package dev.rusyaev.utils.parsing;

import dev.rusyaev.entity.Address;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public interface ParserAddress {
    void parsing(String filePath, Collection<Address> collection) throws FileNotFoundException;
}

package dev.rusyaev.utils.parsing;

import dev.rusyaev.entity.Address;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public abstract class AbstractParserAddress implements ParserAddress {
    @Override
    public void parsing(String filePath, Collection<Address> collection) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                try {
                    String fullAddress = reader.readLine();

                    collection.add(getAddress(fullAddress));
                } catch (Exception ignoreBecauseIncorrectData) {}
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    abstract Address getAddress(String parseLine);
}

package dev.rusyaev.config;

import dev.rusyaev.utils.parsing.CSVParserAddress;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    private static final Properties PROPERTIES = new Properties();
    private static final Logger logger = Logger.getLogger(Config.class.getName());

    public static synchronized String getProperty(String name) {
        if(PROPERTIES.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
                PROPERTIES.load(is);
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }

        return PROPERTIES.getProperty(name);
    }
}

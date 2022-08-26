package dev.rusyaev.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties PROPERTIES = new Properties();

    public static synchronized String getProperty(String name) {
        if(PROPERTIES.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
                PROPERTIES.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return PROPERTIES.getProperty(name);
    }
}

package dev.rusyaev.utils;

import dev.rusyaev.Main;

import java.nio.file.Path;
import java.util.logging.Logger;

public class Extension {
    public static String getExtension(String pathToFile) {
        return getExtensionService(pathToFile);
    }

    public static String getExtension(Path pathToFile) {
        return getExtensionService(String.valueOf(pathToFile.getFileName()));
    }

    private static String getExtensionService(String pathToFile) {
        int positionExt = pathToFile.lastIndexOf(".");

        if (positionExt != -1)
            return pathToFile.substring(positionExt + 1).trim().toLowerCase();
        else
            return "";
    }
}

package petstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    static Logger log = LoggerFactory.getLogger("ConfigProperties");

    private static final Properties props = new Properties();
    private static final String PATH_TO_RESOURCES;
    public static final String env;

    public static final String API_URL;
    public static final String USER;
    public static final String PASS;

    static {
        env = System.getProperty("env", "dev");
        log.info("C  O  N  F  I  G  U  R  A  T  I  O  N");
        log.info("Environment : " + env);
        PATH_TO_RESOURCES = "src/test/resources/env/"+env;
        readProperties();

        API_URL = getProperty("url");
        USER = getProperty("default.user");
        PASS = getProperty("default.pass");
    }

    private static void readProperties() throws AssertionError {
        String path = PATH_TO_RESOURCES + "/config.properties";
        try {
            log.info("Reading configuration data from resources file {}", path);
            props.load(new FileReader(path));
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            throw new AssertionError(String.format("An exception occurs during loading of '%s' config file", path), e);
        }
    }

    private static String getProperty(String propertyName, String defaultValue) {
        return System.getProperty(propertyName.toLowerCase(), props.getProperty(propertyName, defaultValue));
    }

    private static String getProperty(String propertyName) {
        String propertyValue = getProperty(propertyName, null);
        log.info("Read property {} = {}", propertyName, propertyValue);
        return propertyValue;
    }

    private static boolean getBooleanProperty(String propertyName) {
        return Boolean.parseBoolean(getProperty(propertyName));
    }

    private static int getIntegerProperty(String propertyName) {
        return Integer.parseInt(getProperty(propertyName));
    }

    private static int getIntegerProperty(String propertyName, int defaultValue) {
        return  Integer.parseInt(System.getProperty(propertyName.toLowerCase(), props.getProperty(propertyName, defaultValue + "")));
    }
}

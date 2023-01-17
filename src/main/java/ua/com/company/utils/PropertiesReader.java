package ua.com.company.utils;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
//    private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);
    private static final Logger log = LogManager.getLogger(PropertiesReader.class);
    private static Properties prop;

    /**
     * Method read and return API key from properties file.
     * @return String representation of bot API key.
     */
    public static String getToken() {
        try (InputStream inputStream = new FileInputStream("src/main/resources/discord.properties")) {
            prop = new Properties();
            prop.load(inputStream);
            log.debug("warn");

        } catch (FileNotFoundException e) {
            log.error("File discord.properties not found. Exiting");
            System.exit(1);
        } catch (IOException e) {
            log.error("Can`t read discord.properties file. Exiting");
            System.exit(1);
        }
        return prop.getProperty("discord.token");
    }
}

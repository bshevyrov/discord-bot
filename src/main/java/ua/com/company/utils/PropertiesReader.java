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
    private static final Logger log = LogManager.getLogger(PropertiesReader.class);
    private static Properties prop;

    /**
     * Method read properties file.
     *
     */
    private static void loadProperties() {
//        try (InputStream inputStream = new FileInputStream("src/main/resources/discord.properties")) {
        try (InputStream inputStream = new FileInputStream("discord.properties")) {
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
    }

    public static String getToken() {
        loadProperties();
        return prop.getProperty("discord.token");
    }

    public static String getGuild() {
        loadProperties();
        return prop.getProperty("discord.guild");
    }
    public static String getChannel() {
        loadProperties();
        return prop.getProperty("discord.channel");
    }

}

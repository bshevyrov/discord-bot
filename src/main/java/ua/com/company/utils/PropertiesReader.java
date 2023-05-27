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
     */
    private static void loadDiscordProperties() {

        String pathToPropFile = "discord.properties";
        ///etc/environment
        if (System.getenv("SERVER_ENVIRONMENT") != null && System.getenv("SERVER_ENVIRONMENT").equals("DEV")) {
            pathToPropFile = "src/main/resources/discord.properties";
        }
        try (InputStream inputStream = new FileInputStream(pathToPropFile)) {
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

    public static String getBotToken() {
        loadDiscordProperties();
        return prop.getProperty("discord.bot.token");
    }

    public static String getUserToken() {
        loadDiscordProperties();
        return prop.getProperty("discord.user.token");
    }

    public static String getGuild() {
        loadDiscordProperties();
        return prop.getProperty("discord.guild");
    }

    public static String getChannel() {
        loadDiscordProperties();
        return prop.getProperty("discord.channel");
    }

    public static String getDeleteDelay() {
        loadDiscordProperties();
        return prop.getProperty("discord.delay.in.ms.before.del.message");
    }

    public static String getSendDelay() {
        loadDiscordProperties();
        return prop.getProperty("discord.delay.in.ms.before.send.another.dm");
    }

    public static String getManualRoundDelay() {
        loadDiscordProperties();
        return prop.getProperty("discord.delay.in.m.before.new.manual.bump.round");
    }

    public static String getAutoRoundDelay() {
        loadDiscordProperties();
        return prop.getProperty("discord.delay.in.m.before.new.auto.bump.round");
    }

    public static String getDisboardUrl() {
        loadDiscordProperties();
        return prop.getProperty("discord.site.disboard.url");
    }

    public static String getDiscadiaUrl() {
        loadDiscordProperties();
        return prop.getProperty("discord.site.discadia.url");
    }

    public static String getDiscordHomeUrl() {
        loadDiscordProperties();
        return prop.getProperty("discord.site.discordhome.url");
    }

    public static String getDiscordServerInfoUrl() {
        loadDiscordProperties();
        return prop.getProperty("discord.site.discordserverinfo.url");
    }

    public static String getTopGGUrl() {
        loadDiscordProperties();
        return prop.getProperty("discord.site.topgg.url");
    }

    public static String getMyserverGGUrl() {
        loadDiscordProperties();
        return prop.getProperty("discord.site.myservergg.url");
    }

    private static void loadMyAnimeListProperties() {

        String pathToPropFile = "mal.properties";
        ///etc/environment
        if (System.getenv("SERVER_ENVIRONMENT") != null && System.getenv("SERVER_ENVIRONMENT").equals("DEV")) {
            pathToPropFile = "src/main/resources/mal.properties";
        }
        try (InputStream inputStream = new FileInputStream(pathToPropFile)) {
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

    public static String getMALClientId() {
        loadMyAnimeListProperties();
        return prop.getProperty("mal.client.id");
    }


}

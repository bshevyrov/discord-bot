package ua.com.company.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.utils.PropertiesReader;

public class DiscordServerInfo implements SiteBot {

    @Override
    public String getUrl() {
        return PropertiesReader.getDiscordServerInfoUrl();
    }

    public void init() {

        WebDriver driver = getLoggedConfiguredChromeDriver(getUrl());
        Actions actions = new Actions(driver);

//TODO WHAT BEHEVARIO???


        driver.quit();


    }


}
package ua.com.company.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.utils.PropertiesReader;

public class DiscordHome implements SiteBot {
    @Override
    public String getUrl() {
        return PropertiesReader.getDiscordHomeUrl();
    }

    public void init() {

        // Instantiate a ChromeDriver class.
        WebDriver driver = getLoggedConfiguredChromeDriver(getUrl());


        //   ONLY ADMIN BUMP


        Actions actions = new Actions(driver);

//        driver.quit();


    }


}
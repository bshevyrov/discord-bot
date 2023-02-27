package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ua.com.company.utils.PropertiesReader;

public class DiscordServerInfo extends SiteBot {


    public void init() {

        WebDriver driver = getLoggedConfiguredChromeDriver(PropertiesReader.getDiscordServerInfoUrl());

        String successXpath = "/html/body/div[2]/div[1]/div[1]/h3";
        if (driver.findElements(By.xpath(successXpath)).size() > 0
                && driver.findElement(By.xpath(successXpath)).getText().equalsIgnoreCase("SUCCESSFULLY LIKE!")) {
            System.out.println("WELL DONE!");
        }
//4 hour

        driver.quit();


    }


}
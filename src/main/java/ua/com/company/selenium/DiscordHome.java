package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.utils.PropertiesReader;

import java.util.Random;

public class DiscordHome extends SiteBot {
//OK
    public void init() {

        // Instantiate a ChromeDriver class.
        WebDriver driver = getLoggedConfiguredChromeDriver(PropertiesReader.getDiscordHomeUrl());
    Actions actions = getActions();
        String xpathBumpButton ="/html/body/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[3]/a[4]/i";
        WebElement webElement = driver.findElement(By.xpath(xpathBumpButton));
        actions.moveToElement(webElement).contextClick().perform();

        try {
            Thread.sleep(5000 + new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String xpathSuccessfulElement = "/html/body/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div[2]/ul/li[3]";

        if(!driver.findElement(By.xpath(xpathSuccessfulElement)).getText().equalsIgnoreCase("available")){
            System.out.println("DISCORD HOME BUMPED!");
        } else {
            System.out.println("SOMETHING GOES WRONG(");
        }
        //   ONLY ADMIN BUMP
//3 hour


       driver.quit();


    }


}
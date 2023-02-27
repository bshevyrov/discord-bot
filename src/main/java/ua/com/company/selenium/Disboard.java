package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.utils.PropertiesReader;

import java.util.Random;

public class Disboard implements SiteBot {

    @Override
    public String getUrl() {
        return PropertiesReader.getDisboardUrl();
    }

    public void init() {
        WebDriver driver = getLoggedConfiguredChromeDriver(getUrl());
        Actions actions = new Actions(driver);
        if (driver.findElements(By.className("contents-3NembX")).size() > 0) {
            actions.moveToElement(driver.findElement(By.className("contents-3NembX"))).contextClick().perform();
            try {
                Thread.sleep(10000 + new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String successXpath = "/html/body/div[2]/div[1]/div[1]/h3";
        if (driver.findElements(By.xpath(successXpath)).size() > 0
                && driver.findElement(By.xpath(successXpath)).getText().equalsIgnoreCase("SUCCESSFULLY LIKE!")) {
            System.out.println("WELL DONE!");
        }
        driver.quit();
    }
}
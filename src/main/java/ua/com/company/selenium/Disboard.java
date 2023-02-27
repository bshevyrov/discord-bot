package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.utils.PropertiesReader;

import java.util.Random;

public class Disboard extends SiteBot {

//FINISH

    public void init() {
        WebDriver driver = getLoggedConfiguredChromeDriver(PropertiesReader.getDisboardUrl());
        Actions actions = getActions();
        String bumpXpathButton ="/html/body/div[1]/div/div[3]/div[2]/div/div[2]/div/a[3]/span[2]";
        WebElement webElement = driver.findElement(By.xpath(bumpXpathButton));
        actions.moveToElement(webElement).contextClick().perform();
        try {
            Thread.sleep(5000 + new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(webElement.getText().length()==8){
            System.out.println("DISBOARD BUMPED!");
        } else {
            System.out.println("DISBOARD SOMETHING GOES WRONG(");
        }

       //

        driver.quit();
    }
    //2 hour
}
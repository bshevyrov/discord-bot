package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.utils.PropertiesReader;

import java.util.Random;

public class TopGG extends SiteBot {
//del laggin
    public void run() {

        WebDriver driver = getLoggedConfiguredChromeDriver(PropertiesReader.getTopGGUrl());
        Actions actions = getActions();

        String loginToVoteButtonXpath = "/html/body/div[10]/div[4]/div/section/div[2]/a[1]";
        actions.moveToElement(driver.findElement(By.xpath(loginToVoteButtonXpath))).contextClick().perform();
        String controlTextXpath="/html/body/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/main/div[1]/div/div[1]/p";
        if(!driver.findElement(By.xpath(controlTextXpath)).getText().equalsIgnoreCase("You have already voted")){


        try {//wait until ad ends
            Thread.sleep(10000 + new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String voteButtonXpath = "/html/body/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/main/div[1]/div/div[2]/div/div/section/section/section/button";
        actions.moveToElement(driver.findElement(By.xpath(voteButtonXpath))).contextClick().perform();

        try {
            Thread.sleep(5000 + new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTimeInMinToNextExecution(12*60);
            System.out.println("Yes");
        } else {
            setTimeInMinToNextExecution(12*60);
            System.out.println("NO");
        }

//12 hour

        driver.quit();

    }

}
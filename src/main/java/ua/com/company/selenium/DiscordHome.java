package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ua.com.company.logic.bumper.task.SiteScheduleExecute;
import ua.com.company.utils.PropertiesReader;

import java.time.Duration;
import java.util.Random;

public class DiscordHome extends SiteBot {
    //OK


    public void run() {
        WebDriver driver = null;
        try {
            // Instantiate a ChromeDriver class.
            try {
                driver = getLoggedConfiguredChromeDriver(PropertiesReader.getDiscordHomeUrl());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String xpathNextBump = "/html/body/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div[2]/ul/li[3]";
            if (driver.findElement(By.xpath(xpathNextBump)).getText().trim().contains("Available")) {
                String xpathBumpButton = "/html/body/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[3]/a[4]";
                driver.findElement(By.xpath(xpathBumpButton)).click();

                try {
                    Thread.sleep(5000 + new Random().nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setTimeToNextExecution(Duration.ofHours(3));
            } else {
                //TODO DURATION WHAT>
                setTimeToNextExecution(Duration.ofHours(Integer.parseInt(driver.findElement(
                        By.xpath(xpathNextBump)).getText().substring(0, 2).trim())));
            }

            if (!driver.findElement(By.xpath(xpathNextBump)).getText().trim().equalsIgnoreCase("available")) {
                System.out.println("DISCORD HOME BUMPED!");
            } else {
                System.out.println("SOMETHING GOES WRONG(");
            }


            setError(false);
        } finally {
            if (isError()) {
                setError(false);
                setTimeToNextExecution(Duration.ofMinutes(30));
            }
            createNewScheduleTask(new SiteScheduleExecute().getExecutorService());
            driver.quit();
        }
    }



}
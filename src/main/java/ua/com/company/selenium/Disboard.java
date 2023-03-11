/*
package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.com.company.logic.bumper.task.SiteScheduleExecute;
import ua.com.company.utils.PropertiesReader;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Disboard extends SiteBotBAK {

//FINISH

    public void run() {
        WebDriver driver = null;

        try {
            try {
                driver = getLoggedConfiguredChromeDriver(PropertiesReader.getDisboardUrl());
//
//            driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(10));
//            driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
//            System.out.println(driver.manage().timeouts().getScriptTimeout()+ "script");
//            driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(10));


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
//                Thread.sleep(1000*10*60);//wait 10 min
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

/// verify that u a re human xpath /html/body/div[1]/div/div[1]/div/input
            String bumpXpathButton = "/html/body/div[1]/div/div[3]/div[2]/div/div[2]/div/a[3]";
//        String classnameBumpButton =".button.button-bump.is-dark";
//        String cssSelectorBumpButton ="#main-container > div.servers.columns.is-multiline.listing > div > div.actions.columns > div > a.button.button-bump.is-dark";
            WebElement webElement = driver.findElement(By.xpath(bumpXpathButton));
            System.out.println(webElement.getText());
            if (webElement.getText().trim().contains("ПОВЫСИТЬ")) {
                webElement.click();
                //url bump button url=https://disboard.org/ru/server/bump/967764101256331304
//        actions.moveToElement(webElement).contextClick().perform();
                try {
                    Thread.sleep(10000 + new Random().nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              */
/*  try {
                    Thread.sleep(10 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*//*

                if (!webElement.getText().trim().contains("ПОВЫСИТЬ")) {

                    setTimeToNextExecution(Duration.ofHours(2));
                }
                System.out.println("DISBOARD BUMPED!");
            } else {
                String timeToNextBump = webElement.getText();
                List<Integer> time = Arrays.stream(timeToNextBump.split(":"))
                        .limit(2)//skip seconds
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                setTimeToNextExecution(Duration.ofHours(time.get(0)).plusMinutes(time.get(1)));
                System.out.println(timeToNextBump + "disboard");
                System.out.println("DISBOARD SOMETHING GOES WRONG(");
            }

            //
            System.out.println("quit driver");
            setError(false);
//        driver.quit();
        } finally {
//            driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(5));
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));


            System.out.println("finaly");
            if (isError()) {
                setError(false);
                setTimeToNextExecution(Duration.ofMinutes(30));
            }
            createNewScheduleTask(new SiteScheduleExecute().getExecutorService());
            driver.quit();
        }
    }
}*/

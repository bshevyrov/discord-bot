package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.logic.bumper.task.SiteScheduleExecute;
import ua.com.company.utils.PropertiesReader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Disboard extends SiteBot {

//FINISH

    public void run() {
        WebDriver driver = getLoggedConfiguredChromeDriver(PropertiesReader.getDisboardUrl());
        Actions actions = getActions();

        String bumpXpathButton ="/html/body/div[1]/div/div[3]/div[2]/div/div[2]/div/a[3]/span[2]";
        WebElement webElement = driver.findElement(By.xpath(bumpXpathButton));
        if(webElement.getText().equalsIgnoreCase("повысить")){
        actions.moveToElement(webElement).contextClick().perform();
        try {
            Thread.sleep(5000 + new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTimeInMinToNextExecution(3*60);
            System.out.println("DISBOARD BUMPED!");
        } else {
            String timeToNextBump =  webElement.getText();
            List<Integer> time = Arrays.stream(timeToNextBump.split(":"))
                    .limit(2)//skip seconds
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            setTimeInMinToNextExecution(time.get(0)*60+time.get(1));
            System.out.println(timeToNextBump+"disboard");
            System.out.println("DISBOARD SOMETHING GOES WRONG(");
        }

       //

        driver.quit();
        createNewScheduleTask(new SiteScheduleExecute().getExecutorService());
    }


    //2 hour
}
package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ua.com.company.logic.bumper.task.SiteScheduleExecute;
import ua.com.company.utils.PropertiesReader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DiscordServerInfo extends SiteBot {


    public void run() {

        WebDriver driver = getLoggedConfiguredChromeDriver(PropertiesReader.getDiscordServerInfoUrl());

        String successXpath = "/html/body/div[2]/div[1]/div[1]/h3";

        if (!driver.findElement(By.xpath(successXpath)).getText().equalsIgnoreCase("You can like it every 4 hours!") ) {
//            String xpathUntilNextLike = "//*[@id=\"time\"]";
           setTimeInMinToNextExecution(4*60);

            System.out.println("WELL DONE!");
        } else {
            String idUntilNextLike = "time";
            String timeStr = driver.findElement(By.id(idUntilNextLike)).getText()
                    .substring(driver.findElement(By.id(idUntilNextLike)).getText().indexOf(':') + 1).trim();
            List<Integer> time = Arrays.stream(timeStr.split(":"))
                    .limit(2)//skip seconds
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            setTimeInMinToNextExecution(time.get(0) * 60 + time.get(1));
            System.out.println("Not time" + getTimeInMinToNextExecution());
        }
//4 hour
        createNewScheduleTask(new SiteScheduleExecute().getExecutorService());

        driver.quit();

    }



}
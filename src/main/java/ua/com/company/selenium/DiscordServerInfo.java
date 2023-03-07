package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.logic.bumper.task.SiteScheduleExecute;
import ua.com.company.utils.PropertiesReader;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DiscordServerInfo extends SiteBot {


    public void run() {
        System.out.println("run");
        WebDriver driver = null;

        try {
            try {
                System.out.println("111");
                driver = getLoggedConfiguredChromeDriver(PropertiesReader.getDiscordServerInfoUrl());
//
//                driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(10));
//                driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
//                System.out.println(driver.manage().timeouts().getScriptTimeout()+ "script");
//                driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("befor");
            try {
                System.out.println("in");
                Thread.sleep(5000);
                System.out.println("sleep");
                // https://stackoverflow.com/questions/48450594/selenium-timed-out-receiving-message-from-renderer
//            Thread.sleep(1000*10*60);//WAITE FOR DISCORD.COM SOME BUG
                System.out.println("sleep");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after");
            Actions actions = getActions();

            String successXpath = "/html/body/div[2]/div[1]/div[1]/h3";
            System.out.println(driver.findElement(By.xpath(successXpath)).getText());
            if (!driver.findElement(By.xpath(successXpath)).getText().equalsIgnoreCase("You can like it every 4 hours!")) {
//            String xpathUntilNextLike = "//*[@id=\"time\"]";
                setTimeToNextExecution(Duration.ofHours(4));

                System.out.println("WELL DONE!");
            } else {
                String idUntilNextLike = "time";
                String timeStr = driver.findElement(By.id(idUntilNextLike)).getText()
                        .substring(driver.findElement(By.id(idUntilNextLike)).getText().indexOf(':') + 1).trim();
                List<Integer> time = Arrays.stream(timeStr.split(":"))
                        .limit(2)//skip seconds
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                setTimeToNextExecution(Duration.ofHours(time.get(0)).plusMinutes(time.get(1)));
                System.out.println("Not time" + getTimeToNextExecution());
            }
//4 hour

            setError(false);
        } finally {

            if (isError()) {
                setError(false);

                setTimeToNextExecution(Duration.ofMinutes(30));
            }
            createNewScheduleTask(new SiteScheduleExecute().getExecutorService());
            driver.quit();

            System.out.println("finaly");
        }
    }


}
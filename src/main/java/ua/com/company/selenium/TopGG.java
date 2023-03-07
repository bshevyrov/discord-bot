package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.logic.bumper.task.SiteScheduleExecute;
import ua.com.company.utils.PropertiesReader;

import java.time.Duration;
import java.util.Random;

public class TopGG extends SiteBot {
    //del laggin
    public void run() {

        WebDriver driver = null;
        try {


            try {
                driver = getLoggedConfiguredChromeDriver(PropertiesReader.getTopGGUrl());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Actions actions = getActions();

//        String loginToVoteButtonXpath = "/html/body/div[9]/div[4]/div/section/div[2]/a[1]";
//        actions.moveToElement(driver.findElement(By.xpath(loginToVoteButtonXpath))).contextClick().perform();
//       driver.findElement(By.xpath(loginToVoteButtonXpath)).click();
//     actions.pause(Duration.ofSeconds(5));
//        try {
//            driver = getLoggedConfiguredChromeDriver(http://www.top.gg/login?redir=%2Fservers%2F967764101256331304%2Fvote);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String controlTextXpath = "/html/body/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/main/div[1]/div/div[1]/p";
            System.out.println(driver.findElement(By.xpath(controlTextXpath)).getText());
            if (driver.findElement(By.xpath(controlTextXpath)).getText().equalsIgnoreCase("You can vote now!")) {


                try {//wait until ad ends
                    Thread.sleep(10000 + new Random().nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String voteButtonXpath = "/html/body/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div/div/div[1]/main/div[1]/div/div[2]";
//            actions.moveToElement(driver.findElement(By.xpath(voteButtonXpath))).contextClick().perform();
                System.out.println(driver.findElement(By.xpath(voteButtonXpath)).getText());

                driver.findElement(By.xpath(voteButtonXpath)).click();
                try {
                    Thread.sleep(5000 + new Random().nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setTimeToNextExecution(Duration.ofHours(12));
                System.out.println("Yes");
            } else {
                setTimeToNextExecution(Duration.ofHours(12));
                System.out.println("NO");
            }

//12 hour
            setError(false);
        } finally {
            setError(false);
            setTimeToNextExecution(Duration.ofMinutes(30));
            createNewScheduleTask(new SiteScheduleExecute().getExecutorService());
            driver.quit();

        }

    }


   /* @Override
    protected void additionalMoves(JavascriptExecutor js, WebDriver driver) {
        String url = "https://top.gg/servers/967764101256331304/vote";
//        String referer= "document.write('<script>window.location.href = \"https://top.gg/servers/967764101256331304/vote/\";</script>')";
//        js.executeScript(referer);
//
        driver.get(url);
        driver.navigate().refresh();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String loginToVoteButtonXpath = "/html/body/div[9]/div[4]/div/section/div[2]/a[1]";
        new Actions(driver).moveToElement(driver.findElement(By.xpath(loginToVoteButtonXpath))).click().perform();
//

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
    }*/
}
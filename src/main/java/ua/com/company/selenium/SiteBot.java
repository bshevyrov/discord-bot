package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import ua.com.company.utils.PropertiesReader;

import java.util.Random;
import java.util.TimerTask;

public abstract class SiteBot extends TimerTask {
    private long TimeInMinToNextExecution;

    public long getTimeInMinToNextExecution() {
        return TimeInMinToNextExecution;
    }

    public void setTimeInMinToNextExecution(long timeInMinToNextExecution) {
        TimeInMinToNextExecution = timeInMinToNextExecution
                +new Random().nextInt(10)+1;
    }

    private   Actions actions = null;

    public Actions getActions(){
        return  actions;
    }

    public WebDriver getLoggedConfiguredChromeDriver(String url) {

        // Instantiate a ChromeDriver class.
        WebDriver driver = new ChromeDriver(getChromeOptions());

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor) driver;
        actions= new Actions(driver);
        driver.get(url);

        try {
            Thread.sleep(5000 + new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript(loginWithTokenScript(PropertiesReader.getUserToken()));

        try {
            Thread.sleep(5000 + new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (driver.findElements(By.className("contents-3NembX")).size() > 0) {
            actions.moveToElement(driver.findElement(By.className("contents-3NembX"))).contextClick().perform();
            try {
                Thread.sleep(10000 + new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return driver;

    }

    private ChromeOptions getChromeOptions() {

        ChromeOptions chrome = new ChromeOptions();

        chrome.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");
        chrome.addArguments("--window-size=1280,1024");//1280X1024
        chrome.addArguments("--disable-extensions");
        chrome.addArguments("--profile-directory=Default");
        chrome.addArguments("--incognito");
        chrome.addArguments("--disable-plugins-discovery");
        chrome.addArguments("--disable-blink-features");
        chrome.addArguments("--disable-blink-features=AutomationControlled");
        chrome.addArguments("--start-maximized");

//chrome.addArguments("--headless");
        return chrome;
    }

    private String loginWithTokenScript(String token) {
        return " function login(token) {"
                + "setInterval(() => {"
                + "document.body.appendChild(document.createElement `iframe`).contentWindow.localStorage.token = `\"${token}\"`"
                + "}, 50);"
                + "setTimeout(() => {"
                + "location.reload();"
                + "}, 2500);"
                + "}"
                + " login('" + token + "')";
    }
}

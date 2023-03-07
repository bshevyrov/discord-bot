package ua.com.company.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import ua.com.company.utils.PropertiesReader;
import ua.com.company.utils.UA;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class SiteBot implements Runnable {
    private long timeToNextExecution;
    private boolean error = true;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public long getTimeToNextExecution() {
        return timeToNextExecution;
    }

    public void setTimeToNextExecution(Duration timeToNextExecution) {
        this.timeToNextExecution = timeToNextExecution.toMinutes()
                + new Random().nextInt(10) + 1;
    }

    public void createNewScheduleTask(ScheduledExecutorService executor) {
        executor.schedule(this, this.getTimeToNextExecution(), TimeUnit.MINUTES);
    }

    private Actions actions;

    public Actions getActions() {
        return actions;
    }

    public synchronized WebDriver getLoggedConfiguredChromeDriver(String url) throws InterruptedException {
        // Instantiate a ChromeDriver class.
        WebDriver driver = new ChromeDriver(getChromeOptions());

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
//        additionalMoves(js,driver);

            driver.get(url);


        try {
//            Thread.sleep(500000 );
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
        System.out.println(driver.manage().timeouts().getImplicitWaitTimeout() + " implict");
        // Implicit wait timeout for 20seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//        driver.manage().timeouts().pageLoadTimeout(3,TimeUnit.MINUTES);
//        String xpathAuthorizeDiscordButton = "/html/body/div[1]/div[2]/div[1]/div[1]/div/div/div/div/div/div[2]/button[2]/div";
        String xpathAuthorizeDiscordButton ="/html/body/div[1]/div[2]/div[1]/div[1]/div/div/div/div/div/div[2]/button[2]";
//        String selectorAuthorizeDiscordButton ="#app-mount > div.appAsidePanelWrapper-ev4hlp > div.notAppAsidePanel-3yzkgB > div.app-3xd6d0 > div > div > div > div > div > div.footer-3Gu_Tl > button.button-ejjZWC.lookFilled-1H2Jvj.colorBrand-2M3O3N.sizeMedium-2oH5mg.grow-2T4nbg/html/body/div[1]/div[2]/div[1]/div[1]/div/div/div/div/div/div[2]/button[2]";
//        String classnameAuthorizeDiscordButton ="contents-3NembX";
        List< WebElement> list = driver.findElements(By.xpath(xpathAuthorizeDiscordButton));
        System.out.println(list.size() + " size of list");
        if (list.size() != 0) {
            System.out.println("in list");
            driver.findElement(By.xpath(xpathAuthorizeDiscordButton)).click();
//            actions.moveToElement(driver.findElement(By.xpath(xpathAuthorizeDiscordButton))).contextClick().perform();
            System.out.println("after driver");

        }
        try {
            Thread.sleep(10000 + new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver;

    }


    private ChromeOptions getChromeOptions() {

        ChromeOptions chrome = new ChromeOptions();

        chrome.addArguments("user-agent="+ new UA().getUA());
//        chrome.addArguments("user-agent=Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.697.0 Safari/534.24");
        chrome.addArguments("--window-size=1280,1024");//1280X1024
//        chrome.addArguments("--profile-directory=Default");
        chrome.addArguments("--user-data-dir=src/main/resources/chrome");
        chrome.addArguments("--incognito");
        chrome.addArguments("--log-level=3");
        chrome.addArguments("--disable-plugins-discovery");
        chrome.addArguments("--disable-blink-features");
        chrome.addArguments("--disable-blink-features=AutomationControlled");
        chrome.addArguments("--start-maximized");
        chrome.setPageLoadStrategy(PageLoadStrategy.EAGER);
//        chrome.addArguments("--disable-extensions");
        chrome.addExtensions(new File("src/main/resources/extension_1_47_4_0.crx"));
        chrome.addExtensions(new File("src/main/resources/extension_3_7_21_0.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chrome);
        chrome.merge(capabilities);
//        chrome.addArguments("--headless");

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

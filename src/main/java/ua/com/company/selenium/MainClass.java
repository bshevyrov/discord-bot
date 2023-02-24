package ua.com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.CompositeAction;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class MainClass {
    static {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    }

//    Map<String, List<Double>> map = JavaRunCommand.init();

    public void init() {


        ChromeOptions chrome = new ChromeOptions();

        chrome.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");
        chrome.addArguments("--window-size=1280,1024");//1280X1024
         chrome.addArguments("--disable-extensions");
         chrome.addArguments("--profile-directory=Default");
         chrome.addArguments("--incognito");
         chrome.addArguments("--disable-plugins-discovery");
        chrome.addArguments("--disable-blink-features=AutomationControlled");

         chrome.addArguments("--start-maximized");






//chrome.addArguments("--headless");


        // Instantiate a ChromeDriver class.
        WebDriver driver = new ChromeDriver(chrome);

        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Maximize the browser
//        driver.manage().window();
        // Launch Website
        String url = "https://bot.sannysoft.com/";
        driver.get(url);
        try {
            Thread.sleep(14400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(13002);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Actions actions = new Actions(driver);

//        actions.moveByOffset()
//        driver.quit();
       /* WebElement drawer = driver.findElement(By.id("drawer"));
        drawer.getLocation();
        System.out.println( drawer.getLocation().getX());
        System.out.println( drawer.getLocation().getY());
        actions.moveToElement(drawer);
        actions.perform();*/
//        List<Double> x = map.get("x");
//        List<Double> y = map.get("y");
        CompositeAction composit = new CompositeAction();
        Action serias = null;

       /* for (int i = 0; i < x.size(); i++) {
//            System.out.println(x.get(i) +"=x y="+(y.get(i)));
            actions = new Actions(driver);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            actions.moveByOffset((int) Math.round(x.get(i)), (int) Math.round(y.get(i)));
//            serias=actions.moveByOffset((int) (Math.round(x.get(i))), (int) (Math.round(y.get(i)))).build();
//            composit.addAction(serias);


            System.out.println(x.get(i) + " " + y.get(i));

        }*/
        try {
//            actions.build().perform();
//            composit.perform();

        }catch (MoveTargetOutOfBoundsException e){

            System.out.println("out of bounce");
        }


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
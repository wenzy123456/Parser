import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.profiler.model.Profile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.SessionId;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class OspigSessionId {
   // Set <Cookie> allCookies;


    public static void main(String[] args) throws Exception {
        OspigSessionId ospigSessionId = new OspigSessionId();
       // ospigSessionId.getSessionId();
       // SessionId sessionId = new SessionId(ospigSessionId.getSessionId().toString());
        Set<Cookie> allCookies = ospigSessionId.getCookies();
        ChromeDriver webDriver = ospigSessionId.getWebDriver();
        Thread.sleep(2000);
        for(Cookie cookie : allCookies )
        {
           try{
               webDriver.manage().addCookie(cookie);
               Thread.sleep(2000);
           }
        catch (Exception e){
               System.out.println(cookie);
         }

        }
        webDriver.get("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
        webDriver.navigate().to("https://b2b.ospig.de/#/article/23553/0203");
        Ospig.sleep(2);
        webDriver.close();
    }

    public ChromeDriver getWebDriver(){
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        return webDriver;
    }

    public Set<Cookie> getCookies() throws IOException, InterruptedException, NoSuchElementException {
        String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
        ChromeDriver webDriver = getWebDriver();
        webDriver.get(url);
        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
        WebElement btn = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
        login.sendKeys("708145");
        password.sendKeys("Reaba1966");
        btn.click();
        WebElement login1 = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
        WebElement password1 = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
        WebElement btn1 = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
        login1.sendKeys("708145");
        password1.sendKeys("Reaba1966");
        btn1.click();
        Ospig.sleep(40);
        System.out.println(webDriver.getSessionId().toString());
        String url1 = ("https://b2b-shop.ospig.de/default.aspx#/search");
        webDriver.get(url1);
        Ospig.sleep(3);
       Set<Cookie> allCookies =webDriver.manage().getCookies();
        webDriver.close();
        return allCookies;

    }
}
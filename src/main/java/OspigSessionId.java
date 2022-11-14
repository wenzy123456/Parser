import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.profiler.model.Profile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.SessionId;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.NoSuchElementException;

public class OspigSessionId {
   // Set <Cookie> allCookies;


    public static void main(String[] args) throws Exception {
       // OspigSessionId ospigSessionId = new OspigSessionId();
        OspigSessionId.getPage();
       // SessionId sessionId = new SessionId(ospigSessionId.getSessionId().toString());
     /*   Set<Cookie> allCookies = ospigSessionId.getCookies();
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
        webDriver.close();*/
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
        Ospig.sleep(30);
       Set<Cookie> allCookies =webDriver.manage().getCookies();
        webDriver.close();
        return allCookies;
    }
    public static void getPage() throws Exception {
       FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/ospigproov.csv", true);
       BufferedWriter   buff = new BufferedWriter(csvWriter);
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
        ChromeDriver webDriver = new ChromeDriver();
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
        String url1 = ("https://b2b-shop.ospig.de/default.aspx#/search");
        webDriver.get(url1);
        Ospig.sleep(30);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        for ( int j = 0; j < 20; j++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Ospig.sleep(1);
        }

        WebElement el = webDriver.findElement(By.xpath("//*[@id=\"scrollpane\"]/div/div[172]/span/div[1]/div[2]/img"));
        Ospig.sleep(3);                                        //*[@id="scrollpane"]/div/div[112]/span/div[1]/div[2]/img
        Actions action = new Actions(webDriver);                       //*[@id="scrollpane"]/div/div[199]/span/div[1]/div[2]/img
        action.doubleClick(el).perform();                              //*[@id="scrollpane"]/div/div[206]/span/div[1]/div[2]/img
        Ospig.sleep(3);
        List<WebElement> colors = new ArrayList <>();
        colors.addAll(webDriver.findElements(By.className("colorbutton")));
        System.out.println(colors.size());
       int countDiv = 0;
        for (int h=0; h< colors.size();h++){
            System.out.println(colors.get(h).getTagName());
            if(!colors.get(h).getTagName().equals("div")){
                countDiv++;
            }
        }

        for (int g=0; g< colors.size();g++
        ) {

            if ( g >= colors.size()-countDiv){
               continue;
            }
            Actions action1 = new Actions(webDriver);
            action1.doubleClick(colors.get(g)).perform();
            Ospig.sleep(3);
            WebElement code = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[2]/span"));
            WebElement products = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[3]/span"));
            WebElement material = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[4]/div[2]/div/span"));
            WebElement price = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[5]/div[2]/div[2]/div/span[1]/b"));
            WebElement picture = webDriver.findElement(By.id("product_image"));
            WebElement table = webDriver.findElement(By.className("ordertable"));
            List <WebElement> tables = webDriver.findElements(By.className("ordertable"));
            List<WebElement> photos = webDriver.findElements(By.className("img-responsive"));
            for (WebElement eltable : tables
            ) {

                WebElement color = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[7]/div[1]/span[2]"));
                List <WebElement> rows_table = eltable.findElements(By.tagName("tr"));
                List <WebElement> columns_row = rows_table.get(1).findElements(By.tagName("td"));
                LinkedList <WebElement> columns = new LinkedList(columns_row);
                List <WebElement> title_columns = eltable.findElements(By.tagName("th"));
                LinkedList <WebElement> titles = new LinkedList(title_columns);
                LinkedHashMap <String, String> map = new LinkedHashMap <>();

             //  FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/ospig.csv", true);
             //  BufferedWriter buff = new BufferedWriter(csvWriter);

                System.out.println(titles.get(1).getText());
                System.out.println(code.getText());
                System.out.println(products.getText());
                System.out.println(material.getText());
                System.out.println(price.getText());
                System.out.println(color.getText());

                buff.write(code.getText() + "\t " +products.getText() + "\t " + material.getText() + "\t " + price.getText() + "\t " + color.getText() + "\n");

                for (WebElement elm :photos
                     ) {
                    if(elm.getAttribute("src")==null){
                        continue;
                    }
                   System.out.println(elm.getAttribute("src"));
                    buff.write(elm.getAttribute("src")+ "\n");
                }

                for (int i = 0; i < columns.size(); i++) {
                    if (i == 0) {
                        continue;
                    }
                    int j = i;
                    map.put(titles.get(j + 1).getText(), columns.get(i).getText());
                    buff.write(titles.get(j + 1).getText()+" "+ columns.get(i).getText()+ "\n");
                }
                System.out.println(map);

                //  }

     /*   int rows_count = rows_table.size();
        System.out.println(webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[7]/div[1]/span[2]")).getText());
        for (int row = 0; row < rows_count; row++) {
            List <WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            int columns_count = Columns_row.size();
            System.out.println("Number of cells In Row " + row + " are " + columns_count);
            for (int column = 0; column < columns_count; column++) {
                String celtext = Columns_row.get(column).getText();
                System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
            }
        }*/

        /*  for (WebElement webElement :colors
             ) {


            Actions builder = new Actions (webDriver);
            builder.moveToElement(webElement);
            builder.clickAndHold();
            Ospig.sleep(2);
            builder.build().perform();
           // builder.moveToElement(webElement).build().perform();
            WebElement toolTipElement = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[7]/div[2]/div[1]"));
            System.out.println(toolTipElement.getText());

        }*/
            }

      /*   WebElement elm = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[1]/span"));
            Ospig.sleep(1);
            Actions action2 = new Actions(webDriver);
            action2.doubleClick(elm).perform();
            Ospig.sleep(3);*/
        }
        buff.flush();
        buff.close();
        csvWriter.close();
        webDriver.quit();
    }

}
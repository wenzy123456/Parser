import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Photo {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        String url = ("https://www.glo-story.com/login?back=my-account");
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get(url);

        WebElement login = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/div[1]/input"));
        WebElement password =webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/div[2]/span/input"));
        WebElement btn =webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/p[2]/button"));
        login.sendKeys("lipsuke@gmail.com");
        password.sendKeys("Reaba1966");
        btn.click();
        int count = 0;
        List <WebElement> product =new ArrayList <>();
        List<WebElement> price =new ArrayList <>();
        List<WebElement> photo =new ArrayList <>();
        int j=0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/boysphoto5.csv");
        BufferedWriter buff = new BufferedWriter( csvWriter );
        buff.write("Product code\tPair type\tThumbnail\tDetailed image\n");
        for (int i = 1; i < 2; i++) {
            webDriver.get("https://www.glo-story.com/18-boys?p=" + i);
            product.addAll(webDriver.findElements(By.className("product-name")));
            price.addAll(webDriver.findElements(By.className("content_price")));
            photo.addAll(webDriver.findElements(By.className("lazy")));
            Actions builder = new Actions(webDriver);

            for(; j< photo.size() ; j++) {

                if(photo.get(j).getAttribute("style").equals(null)|photo.get(j).getAttribute("style").equals("")){
                    continue;
                }
                     if (product.get(j).getText().equals("")) {
                    continue;
                }
                Pattern pattern = Pattern.compile("[A-Z]{3,4}-\\w{4,5}");
                Matcher matcher = pattern.matcher(product.get(j).getText());
                while (matcher.find()) {

                    buff.write(product.get(j).getText().substring(matcher.start(), matcher.end())+"\tM\t\t" + photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$", "") + "#{[ee]:;[en]:;[ru]:;}" + "\n");

                    count++;
                    System.out.println(count + "  " + photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$", ""));
                }
            }
        }
        buff.flush();
        buff.close();
        csvWriter.close();
   }
}

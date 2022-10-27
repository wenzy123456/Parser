import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GlostoryPage {

    public static void main(String[] args) {
     getPageTableData();
    }

  public static void getPageTableData() {
      System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
      String url = ("https://www.glo-story.com/login?back=my-account");
      ChromeDriver webDriver = new ChromeDriver();
      webDriver.get(url);
      WebElement login = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/div[1]/input"));
      WebElement password = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/div[2]/span/input"));
      WebElement btn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/p[2]/button"));
      login.sendKeys("lipsuke@gmail.com");
      password.sendKeys("Reaba1966");
      btn.click();
      webDriver.get("https://www.glo-story.com/padded-jackets/15810-Women-s-long-puffer-coat-5996631038763.html");

  }





}

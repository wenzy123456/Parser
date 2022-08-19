import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Proovcode {
  public static   List productCode = new ArrayList<>();
 static    FileWriter csvWriter;

    static {
        try {
            csvWriter = new FileWriter("/Users/lipsuke/Desktop/productcodes.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static    BufferedWriter buff = new BufferedWriter( csvWriter );


    public Proovcode() throws IOException {
    }

    public static void main(String[] args) throws IOException {
     Proovcode pc = new Proovcode();
        buff.write("Product code\tProduct type\tLanguage\tPrice\n");
            pc.getCode("https://www.glo-story.com/18-boys?p=",14);
            pc.getCode("https://www.glo-story.com/3-women?p=",74);
            pc.getCode("https://www.glo-story.com/15-men?p=",72);
            pc.getCode("https://www.glo-story.com/17-girls?p=",24);

        buff.flush();
        buff.close();
        csvWriter.close();
    }


   public void getCode(String link, int pageQuantity) throws IOException {
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
       List<WebElement> product =new ArrayList <>();
       int j=0;

       for (int i = 1; i < pageQuantity; i++) {
           //74 webDriver.get("https://www.glo-story.com/3-women?p=" + i);
           //72  webDriver.get("https://www.glo-story.com/15-men?p=" + i);
           //24 webDriver.get("https://www.glo-story.com/17-girls?p=" + i);
           webDriver.get(link + i);
           product.addAll(webDriver.findElements(By.className("product-name")));
           for(; j< product.size() ; j++) {
               if (product.get(j).getText().equals("")) {
                   continue;
               }
               Pattern pattern = Pattern.compile("[A-Z]{3,4}-\\w{4,5}");
               Matcher matcher = pattern.matcher(product.get(j).getText());
               while (matcher.find()) {

                   buff.write(product.get(j).getText().substring(matcher.start(), matcher.end())+ "\tP\tru\tGlo-Story\t"+product.get(j).getText()+"\n");
                   productCode.add(product.get(j).getText().substring(matcher.start(), matcher.end()).split("-"));
                   count++;
                   System.out.println(count + " " + product.get(j).getText().substring(matcher.start(), matcher.end()));

               }
           }
       }

   }


}
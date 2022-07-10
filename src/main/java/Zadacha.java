import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zadacha {

    static FileWriter csvWriter;

    static {
        try {
            csvWriter = new FileWriter("/Users/lipsuke/Desktop/codes.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static BufferedWriter buff = new BufferedWriter( csvWriter );


    public static void main(String[] args) throws IOException {
       new Zadacha().findCode("https://www.glo-story.com/18-boys?p=", 2);
    }


    public static List <String> findCode(String link, int pageQuantity) throws IOException {
        ArrayList<String> codes =new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        int count = 0;
        List <WebElement> product =new ArrayList <>();
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


                       codes.add(product.get(j).getText().replaceAll("[^0-9]", ""));
                   // String[] s = product.get(j).getText().substring(matcher.start(), matcher.end()).split("-");
                   // codes.addAll(List.of(s));
                    count++;
                  // System.out.println(count + " " + product.get(j).getText().replaceAll("[^0-9]", ""));
                    }
                }
                return codes;
            }
        }





import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.devtools.v85.dom.DOM.focus;

public class Parser {

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
        List<WebElement> product =new ArrayList <>();
        List<WebElement> price =new ArrayList <>();
        List<WebElement> photo =new ArrayList <>();
        int j=0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/boysphoto5.csv");
        BufferedWriter buff = new BufferedWriter( csvWriter );
        for (int i = 1; i < 14; i++) {
          //74 webDriver.get("https://www.glo-story.com/3-women?p=" + i);
          //72  webDriver.get("https://www.glo-story.com/15-men?p=" + i);
           //24 webDriver.get("https://www.glo-story.com/17-girls?p=" + i);
            webDriver.get("https://www.glo-story.com/18-boys?p=" + i);
            product.addAll(webDriver.findElements(By.className("product-name")));
            price.addAll(webDriver.findElements(By.className("content_price")));
            photo.addAll(webDriver.findElements(By.className("lazy")));
                Actions builder = new Actions(webDriver);

            for(; j< photo.size() ; j++) {
                // for (WebElement el: product){

                  //  if(j%2==0){
                   //      continue;
                  //    }

                     if(photo.get(j).getAttribute("style").equals(null)|photo.get(j).getAttribute("style").equals("")){
                        continue;
                      }

            /*    if (product.get(j).getText().equals("")) {
                    continue;
                }
                Pattern pattern = Pattern.compile("[A-Z]{3,4}-\\w{4,5}");
                Matcher matcher = pattern.matcher(product.get(j).getText());
                while (matcher.find()) {*/


                       buff.write(photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$","")+"#{[ee]:;[en]:;[ru]:;}"+"\n");
                    //  buff.write( price.get(j).getText() + "\n" );
                  //  buff.write(product.get(j).getText().substring(matcher.start(), matcher.end()) + "\n");

                    count++;
                  //  System.out.println(count + " " + product.get(j).getText().substring(matcher.start(), matcher.end()));
                    //  System.out.println(count +" "+price.get(j).getText());
                     System.out.println(count +"  "+ photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$",""));
               //photo.get(j).click();
                }
          //  }

            //   WebElement tovar = webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.right-block > h5 > a"));
             //   WebElement tovarprice = webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.right-block > div.content_price > span"));
              //  WebElement picture = webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.left-block > div > a.product_img_link.xxx > div > div.lazy.img-hover.bg-image.bg-menuproduct"));
              //  System.out.println(tovar.getText() + " price  " + tovarprice.getText() + "" + picture.getAttribute("style"));
                // picture.click();
        }
        buff.flush();
        buff.close();
        // csvWriter.flush();
        csvWriter.close();





       /* WebElement womans =webDriver.findElement(By.xpath("//*[@id=\"mega_menu_plus\"]/ul/li[6]/a/span"));
        womans.click();
        WebElement tovar =webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.right-block > h5 > a"));
        WebElement tovarprice =webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.right-block > div.content_price > span"));
        WebElement picture = webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.left-block > div > a.product_img_link.xxx > div > div.lazy.img-hover.bg-image.bg-menuproduct"));
        System.out.println(tovar.getText()+ " price  "+tovarprice.getText());
        picture.click();
        WebElement photo =webDriver.findElement(By.cssSelector("#center_column > div > div > div > div.pb-left-column.col-xs-12.col-sm-6.col-md-6.col-lg-8 > div > div:nth-child(1) > a > div > img"));
        System.out.println(photo.getAttribute("src"));
        WebElement womans1 =webDriver.findElement(By.xpath("//*[@id=\"mega_menu_plus\"]/ul/li[6]/a/span"));
        womans1.click();
        WebElement tovar1 =webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(7) > div > div.right-block > h5 > a"));
        WebElement tovarprice1 =webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(10) > div > div.right-block > div.content_price > span"));
        WebElement picture1 = webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(10) > div > div.left-block > div > a.product_img_link.xxx > div > div.lazy.img-hover.bg-image.bg-menuproduct"));
        System.out.println(tovar1.getText()+ " price  "+tovarprice1.getText());
        System.out.println(picture1.getAttribute("style"));
        picture1.click();
        WebElement photo1 =webDriver.findElement(By.cssSelector("#center_column > div > div > div > div.pb-left-column.col-xs-12.col-sm-6.col-md-6.col-lg-8 > div > div:nth-child(1) > a > div > img"));
        System.out.println(photo1.getAttribute("src"));
        WebElement womans2 =webDriver.findElement(By.xpath("//*[@id=\"mega_menu_plus\"]/ul/li[6]/a/span"));
        womans2.click();
   /*     ChromeDriver webDriver = new ChromeDriver();
        List<WebElement> womangoods = new ArrayList <>();
        for (int i = 1; i < 3; i++) {
            webDriver.get("https://www.glo-story.com/3-women?p=" + i);
            List<WebElement> el = webDriver.findElements(By.className("product-name"));
            System.out.println(el);
        }*/
    //getElements();
    }

        public static void getElements() throws Exception {

            StringBuilder sbwg = new StringBuilder();
            StringBuilder sbwgp = new StringBuilder();
            List<String> woomangoods = new ArrayList<>();
            List<String> woomangoodsprice = new ArrayList<>();
           // String url = "https://www.glo-story.com/3-women?p=";
            for (int i = 1; i < 74; i++) {
               //  System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
               // ChromeDriver webDriver = new ChromeDriver();
              // webDriver.get("https://www.glo-story.com/3-women?p=" + i);
              // String element = webDriver.findElement(By.className("price_product-price")).getText();
                Document page = Jsoup.parse(new URL("https://www.glo-story.com/3-women?p=" + i), 60000);
               // String element = page.select("#center_column > div > ul > li:nth-child(1) > div > div.right-block > h5 > a").text();
                String tovar = page.getElementsByClass("product-name").text();
                String price = page.getElementsByClass("content-price").text();
                sbwg.append(tovar);
                woomangoods.add(tovar);
                sbwgp.append(price);
                woomangoodsprice.add(price);
            }
            System.out.println(sbwg);
            System.out.println(sbwgp);
            System.out.println(woomangoodsprice.size());
        }
    }


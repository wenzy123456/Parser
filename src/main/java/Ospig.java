import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ospig {

    public static void main(String[] args) throws IOException {
        Ospig ospig = new Ospig();
        ospig.getData();
    }

    public void getData() throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get(url);
        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
        WebElement btn = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
        // this.sleep(5);
        login.sendKeys("708145");
        password.sendKeys("Reaba1966");
        //   this.sleep(5);
        btn.click();
        //  this.sleep(3);
        WebElement login1 = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
        WebElement password1 = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
        WebElement btn1 = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
        // this.sleep(3);
        login1.sendKeys("708145");
        password1.sendKeys("Reaba1966");
        // this.sleep(5);
        btn1.click();
        this.sleep(40);
        String urlSearch = "https://b2b-shop.ospig.de/default.aspx#/search";
        webDriver.get(urlSearch);
        this.sleep(30);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        List <WebElement> product = new ArrayList <>();
       for (int i = 0; i < 50; i++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            this.sleep(1);
        }
       product.addAll(webDriver.findElements(By.className("img")));
       System.out.println(product.size());
      // String wel = webDriver.findElement(By.xpath("//*[@id=\"scrollpane\"]/div/div[560]/span/div[1]/div[2]/img")).getAttribute("src");
      // System.out.println(wel);
        //int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/ospigdata.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);

     /*   for (WebElement el : product) {
            ++count;
            System.out.println(count+ "\t"+ el.getText());
       }*/
       List <String> photo = new LinkedList <>();
        List<String>code = new LinkedList <>();
        List<String> name = new LinkedList <>();
        List<String> price =new LinkedList <>();
        for(int i=1;i<=product.size();i++) {
            photo.add(webDriver.findElement(By.xpath("//*[@id=\"scrollpane\"]/div/div[" + i + "]/span/div[1]/div[2]/img")).getAttribute("src"));
            code.add(webDriver.findElement(By.xpath("//*[@id=\"scrollpane\"]/div/div["+i+"]/span/div[2]/div[2]/div[1]/a")).getText());
            name.add(webDriver.findElement(By.xpath("//*[@id=\"scrollpane\"]/div/div["+i+"]/span/div[2]/div[2]/div[2]/a")).getText());
            WebElement cena = webDriver.findElement(By.xpath("//*[@id=\"scrollpane\"]/div/div["+i+"]/span/div[2]/div[2]/div[3]/div/div/span[2]"));
            Pattern pattern = Pattern.compile("^[0-9]*[.,]?[0-9]{1,2}");
            Matcher matcher = pattern.matcher(cena.getText());
            String floatPrice ="";
            while (matcher.find()) {
                 floatPrice = new DecimalFormat("#0.00").format(Float.parseFloat(cena.getText().substring(matcher.start(), matcher.end()).replace(",", ".")) * 2);
                price.add(floatPrice);
            }
        }
        Glostory.getFinishedCodes(code);
        for(int i=0;i<product.size();i++) {
            buff.write(code.get(i) + "\t "+name.get(i)+ "\t" + price.get(i) + "\t " + photo.get(i) + "\n");
        }
        int count =0;
        for (String el:photo)
              {
                  count++;
                  System.out.println(count+" " + el);
        }
        int count1 =0;
        for (String el:code)
        {
            count1++;
            System.out.println(count1+" " + el);
        }
        int count2 =0;
        for (String el:price)
        {
            count2++;
            System.out.println(count2+" " + el);
        }
        buff.flush();
        buff.close();
        csvWriter.close();
        webDriver.close();
        /*   webDriver.findElement(By.xpath("//*[@id=\"scrollpane\"]/div/div["+i+"]/span/div[1]/div[2]/img")).click();
            this.sleep(3);
            WebElement code = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[2]/span"));
            WebElement products = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[3]/span"));
            WebElement material = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[4]/div[2]/div/span"));
            WebElement price = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[5]/div[2]/div[2]/div/span[1]/b"));
            WebElement picture = webDriver.findElement(By.id("product_image"));
           // WebElement picture1 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_1\"]"));
          //  WebElement picture2 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_2\"]"));
           // WebElement picture3 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_3\"]"));
           // WebElement picture4 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_4\"]"));
          //  WebElement picture5 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_5\"]"));
           // WebElement table = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[8]/div[1]/div[2]/div/table/tbody[1]/tr/th[1]"));
            Pattern pattern = Pattern.compile("^[0-9]*[.,]?[0-9]{1,2}");
            Matcher matcher = pattern.matcher(price.getText());
            while (matcher.find()) {
                String floatPrice = new DecimalFormat("#0.00").format(Float.parseFloat(price.getText().substring(matcher.start(), matcher.end()).replace(",", ".")) * 2);
                buff.write(code.getText()+"\t "+products.getText()+"\t "+ floatPrice +"\t "+ picture.getAttribute("src")+ "\n");
               // System.out.println(count + " " + price.get(k).getText() + " " + floatPrice);
            }


           // System.out.println(picture.getAttribute("href"));
          //  System.out.println(picture1.getAttribute("src"));
          //  System.out.println(picture2.getAttribute("src"));
           // System.out.println(picture3.getAttribute("src"));
            //System.out.println(picture4.getAttribute("src"));
           // System.out.println(picture5.getAttribute("src"));

            WebElement back =webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[1]/span"));
              back.click();
            sleep(3);


          //  System.out.println(count + " " + el.getText());
          //  buff.write(el.getText() + "\n");

       }
        buff.flush();
        buff.close();
        csvWriter.close();
        webDriver.close();*/
    }

   /*    Document doc = Jsoup.connect("https://b2b-shop.ospig.de/default.aspx#/search").get();
        Elements links = doc.select("[href]");
        List <String> listLinks = new LinkedList <>();
        for (Element el : links) {
                listLinks.add(el.attr("abs:href"));
            }
        for (String el:listLinks)
              {
        System.out.println(el);
        }


        String urlMain = "https://b2b-shop.ospig.de/default.aspx#/article/19235/1429";
        webDriver.get(urlMain);
        this.sleep(5);
        WebElement code = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[2]/span"));
        WebElement product = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[3]/span"));
        WebElement material = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[4]/div[2]/div/span"));
        WebElement price = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[5]/div[2]/div[2]/div/span[1]/b"));
        WebElement picture = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[1]/div[2]/a"));
        WebElement picture1 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_1\"]"));
        WebElement picture2 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_2\"]"));
        WebElement picture3 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_3\"]"));
        WebElement picture4 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_4\"]"));
        WebElement picture5 = webDriver.findElement(By.xpath("//*[@id=\"img_thumb_5\"]"));
        WebElement table = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[8]/div[1]/div[2]/div/table/tbody[1]/tr/th[1]"));
        System.out.println(code.getText()+" "+product.getText()+" "+material.getText()+" "+price.getText());
        System.out.println(picture.getAttribute("href"));
        System.out.println(picture1.getAttribute("src"));
        System.out.println(picture2.getAttribute("src"));
        System.out.println(picture3.getAttribute("src"));
        System.out.println(picture4.getAttribute("src"));
        System.out.println(picture5.getAttribute("src"));
        System.out.println(table.getText());
        WebElement back =webDriver.findElement(By.xpath("//*[@id="page-wrapper"]/div[1]/span"));
    */
       //this.sleep(120);
       // this.sleep(120);
       // Document doc = Jsoup.connect("https://b2b-shop.ospig.de/default.aspx#/search").get();
       // Elements links = doc.select("a[href]");
      //  for (Element el : links) {
        //    System.out.println(el.attr("abs:href"));
        //    }
     //   List <WebElement> product = new ArrayList <>();
      // product.addAll(webDriver.findElements(By.className("product_link")));
       // product.addAll(webDriver.findElements(By.id("page-wrapper")));
      //  product.addAll(webDriver.findElements(By.tagName("img")));
      // Document doc = Jsoup.connect("https://b2b-shop.ospig.de/default.aspx#/search").get();
       // this.sleep(180);
       // Elements links = doc.getElementsByClass("product_link");
    /*    int count = 0;
       for (WebElement el : product)
         {

           System.out.println(++count +" "+el.getText());
      }*/
   // }

      //  List <WebElement> price = new ArrayList <>();
      //  price.add(webDriver.findElement(By.cssSelector("#scrollpane > div > div:nth-child(1) > span > div.box-bottom > div.product_data > div:nth-child(3) > div > div > span:nth-child(2)")));

      //  for (WebElement el:price
       //      ) {
      //    System.out.println(el.getText());
       // }

        // this.sleep(3);
     /*   int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/prices.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
        // buff.write("Product code\tProduct type\tLanguage\tPrice\tDetailed image\n");

        int k = 0;

        for (int i = 1; i < pageQuantity; i++) {
            // webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.get(link + i);
           /* while (webDriver.findElement(By.className("content_price").) {
                if (webDriver.findElement(By.className("content_price")).equals("")) {
                    price.add(null);
                }
                price.add(webDriver.findElement(By.className("content_price")));

            }*/

            // product.addAll(webDriver.findElements(By.className("product-name")));

       /*     price.addAll(webDriver.findElements(By.className("content_price")));

            // photo.addAll(webDriver.findElements(By.className("lazy")));
            // System.out.println(product.size()+" "+price.size()+" "+ photo.size());

            for (; k < price.size(); k++) {
                if(price.get(k).getText().equals("Price - After login")){
                    buff.write("\tP\tru\t" + " " + "\n");
                }

                Pattern pattern = Pattern.compile("^[0-9]*[.,]?[0-9]{1,2}");
                Matcher matcher = pattern.matcher(price.get(k).getText());
                while (matcher.find()) {
                    count++;
                    String floatPrice = new DecimalFormat("#0.00").format(Float.parseFloat(price.get(k).getText().substring(matcher.start(), matcher.end()).replace(",", ".")) * 2.5);
                    buff.write("\tP\tru\t" + floatPrice + "\n");
                    // }
                    //     buff.write("\tP\tru\t" + price.get(k).getText().substring(matcher.start(), matcher.end())+ "\n");
                    System.out.println(count + " " + price.get(k).getText() + " " + floatPrice);
                    //     buff.write(product.get(j).getText().substring(matcher.start(), matcher.end()) + "\tP\tru\t" + price.get(j).getText().replaceAll("[^0-9,.]", "")+"\t"+ photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$","")+"#{[ee]:;[en]:;[ru]:;}"+"\n");

                }
            }
        }

        buff.flush();
        buff.close();
        csvWriter.close();*/
  //  }
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {

        }
    }

}

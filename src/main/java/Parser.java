import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.net.URL;

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
        WebElement womans =webDriver.findElement(By.xpath("//*[@id=\"mega_menu_plus\"]/ul/li[6]/a/span"));
        womans.click();
        WebElement tovar =webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.right-block > h5 > a"));
        WebElement tovarprice =webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.right-block > div.content_price > span"));
        WebElement picture = webDriver.findElement(By.cssSelector("#center_column > div > ul > li:nth-child(1) > div > div.left-block > div > a.product_img_link.xxx > div > div.lazy.img-hover.bg-image.bg-menuproduct"));
        System.out.println(tovar.getText()+ " price  "+tovarprice.getText());
        picture.click();
        WebElement photo =webDriver.findElement(By.cssSelector("#center_column > div > div > div > div.pb-left-column.col-xs-12.col-sm-6.col-md-6.col-lg-8 > div > div:nth-child(1) > a > div > img"));
        System.out.println(photo.getAttribute("src"));
        //String element = webDriver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div/div[2]/div/div[2]/h1")).getText();
       // String element1 = webDriver.findElement(By.xpath("//*[@id=\"our_price_display\"]/text()")).getText();
       // System.out.println(womans.getText());
        //System.out.println(getElement(url));
    }


    public static void getElement(String url) throws IOException {
     //   String url = ("https://www.glo-story.com/jackets/12979-Women-s-leather-jacket-5996525323579.html");
        Document page = Jsoup.parse(new URL(url), 6000);
       // String element = page.getElementsByClass("small").text();
       Elements lg = page.selectXpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/div[1]/input");
       Elements pw = page.selectXpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/div[2]/span/input");
        lg.val("lipsuke@gmail.com");
        pw.val("Reaba1966");
    }



    public static Document getPage(String url) throws IOException {
     //   String url = ("https://www.glo-story.com/jackets/12979-Women-s-leather-jacket-5996525323579.html");
        Document page = Jsoup.parse(new URL(url), 6000);
        return page;
    }

    }
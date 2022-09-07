import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Glostory {

    public static void main(String[] args) throws Exception {
        Glostory pc = new Glostory();
       // pc.getCode("https://www.glo-story.com/18-boys?p=", 17);
       // pc.getCode("https://www.glo-story.com/3-women?p=",99);
      //  pc.getCode("https://www.glo-story.com/15-men?p=",80);
       // pc.getCode("https://www.glo-story.com/17-girls?p=",33);

        // pc.getPrice("https://www.glo-story.com/18-boys?p=", 17);
        // pc.getPrice("https://www.glo-story.com/3-women?p=",99);
        // pc.getPrice("https://www.glo-story.com/15-men?p=",80);
        // pc.getPrice("https://www.glo-story.com/17-girls?p=",33);

      //  pc.getPhoto("https://www.glo-story.com/18-boys?p=", 17);
      //  pc.getPhoto("https://www.glo-story.com/3-women?p=",99);
      //  pc.getPhoto("https://www.glo-story.com/15-men?p=",80);
       // pc.getPhoto("https://www.glo-story.com/17-girls?p=",33);
        pc.getPicture();
    }


    public void getCode(String link, int pageQuantity) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        List<String>finishCodes =new ArrayList<>();
        int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/glostorycodes.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
       //  buff.write("Product code\tProduct type\tLanguage\tPrice\tDetailed image\n");
        List <WebElement> product = new ArrayList <>();
        int j = 0;
        for (int i = 1; i < pageQuantity; i++) {
            webDriver.get(link + i);
            product.addAll(webDriver.findElements(By.className("product-name")));
            for (; j < product.size(); j++) {
                if (product.get(j).getText().equals("")) {
                    continue;
                }
                Pattern pattern = Pattern.compile("[A-Z]{3,4}-\\w{4,5}");
                Matcher matcher = pattern.matcher(product.get(j).getText());
                while (matcher.find()) {
                    finishCodes.add(product.get(j).getText().substring(matcher.start(), matcher.end()));
                    buff.write(product.get(j).getText() + "\n");
                    count++;
                }
            }
        }
        buff.flush();
        buff.close();
        csvWriter.close();
        getFinishedCodes(finishCodes);
        webDriver.close();
    }

    public void getPrice(String link, int pageQuantity) throws IOException {
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
        int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/glostoryprices.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
        // buff.write("Product code\tProduct type\tLanguage\tPrice\tDetailed image\n");
        List <WebElement> price = new ArrayList <>();
        int k = 0;

        for (int i = 1; i < pageQuantity; i++) {
            webDriver.get(link + i);
            price.addAll(webDriver.findElements(By.className("content_price")));
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
                    System.out.println(count + " " + price.get(k).getText() + " " + floatPrice);
                }
            }
        }

        buff.flush();
        buff.close();
        csvWriter.close();
        webDriver.close();
    }

    public void getPhoto(String link, int pageQuantity) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/glostoryphotos.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
        // buff.write("Product code\tProduct type\tLanguage\tPrice\tDetailed image\n");
        List <WebElement> photo = new ArrayList <>();
        int j = 0;
        for (int i = 1; i < pageQuantity; i++) {
            webDriver.get(link + i);

            photo.addAll(webDriver.findElements(By.className("lazy")));
            for (; j < photo.size(); j++) {
                if (photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$", "").equals("")) {
                    continue;
                }
                if(photo.get(j).isDisplayed()) {
                    buff.write(photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$", "") + "\n");
                    count++;
                    System.out.println(count + " " + photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$", ""));
                }
           }
        }
        buff.flush();
        buff.close();
        csvWriter.close();
        webDriver.close();
        
    }

    public void getPicture() throws IOException {
        Map loginCookies = new HashMap<>();
        for(int i=0;i<20;i++) {
            String url = ("https://b2b.ospig.de/login.aspx?ReturnUrl=%2f");
            //  String url = ("https://www.glo-story.com/login?back=my-account");
            Connection.Response res = Jsoup.connect(url)
                    .data("username", "708145", "password", "Reaba1966")
                    .method(Connection.Method.POST)
                    .execute();
            // String sessionId = res.cookie("SESSIONID");
             loginCookies = res.cookies();
            System.out.println(loginCookies.toString());
        }
            String urlSearch = "https://b2b-shop.ospig.de/default.aspx#/search";
            // String urlSearch = "https://www.glo-story.com/3-women";
            Document doc1 = Jsoup.connect(urlSearch).cookies(loginCookies).get();

        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/ospig.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
       // String urlSearch = "https://b2b-shop.ospig.de/default.aspx#/search";
        List <Element> product = new ArrayList <>();
            Elements elements = doc1.getElementsByClass("img");
            product.addAll(elements);
            System.out.println(product.size());

       List <String> photo = new LinkedList <>();
        List<String>code = new LinkedList <>();
        List<String> name = new LinkedList <>();
        List<String> price =new LinkedList <>();
        for(int i=1;i<=product.size();i++) {
            photo.add(doc1.selectXpath("//*[@id=\"scrollpane\"]/div/div[" + i + "]/span/div[1]/div[2]/img").get(0).attr("src"));
            code.add(doc1.selectXpath("//*[@id=\"scrollpane\"]/div/div["+i+"]/span/div[2]/div[2]/div[1]/a").get(0).text());
            name.add(doc1.selectXpath("//*[@id=\"scrollpane\"]/div/div["+i+"]/span/div[2]/div[2]/div[2]/a").get(0).text());
            Element cena = doc1.selectXpath("//*[@id=\"scrollpane\"]/div/div["+i+"]/span/div[2]/div[2]/div[3]/div/div/span[2]").get(0);
            Pattern pattern = Pattern.compile("^[0-9]*[.,]?[0-9]{1,2}");
            Matcher matcher = pattern.matcher(cena.text());
            String floatPrice ="";
            while (matcher.find()) {
                floatPrice = new DecimalFormat("#0.00").format(Float.parseFloat(cena.text().substring(matcher.start(), matcher.end()).replace(",", ".")) * 2);
                price.add(floatPrice);
            }
        }
        System.out.println(photo.size()+" "+code.size()+" "+name.size());
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
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {

        }
    }
    public static void getFinishedCodes(List<String>code) throws IOException {
        List<String> newcode = new LinkedList<>();
        for (int i = 0; i < code.size(); i++) {
            String s1 = code.get( i );
            for (int j = 0; j < code.size(); j++) {
                String s2 = code.get( j );
                if (i == j) {
                    continue;
                }
                if (s1.equals( s2 )) {
                    newcode.add( s2 );
                }
            }
        }
        Iterator iterator = newcode.iterator();
        List<String> fin = new LinkedList<>();
        Set<String> newfin = new LinkedHashSet<>();

        while (iterator.hasNext()) {
            fin.add( String.valueOf( iterator.next() ) );
        }
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < fin.size(); i++) {
            int count = 0;
            for (int j = 0; j < code.size(); j++) {
                if (code.get( j ).equals( fin.get( i ) )) {
                    map.put( j, String.valueOf( new StringBuilder( code.get( j ) ).append( "-" ).append( count++ ) ).replaceAll( "[0]+$", "" ).replaceAll( "[-]+$", "" ) );
                }
            }
        }

        for (int j = 0; j < code.size(); j++) {
            for (Map.Entry<Integer, String> item : map.entrySet()) {
                code.set( item.getKey(), item.getValue() );
            }
        }
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/glostoryfinishedcodes.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
        for (String el : code) {
            buff.write( el+ "\n" );
        }
        buff.flush();
        buff.close();
        csvWriter.close();
    }
}
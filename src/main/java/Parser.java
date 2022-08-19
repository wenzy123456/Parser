import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static void main(String[] args) throws Exception {
        Parser pc = new Parser();
        pc.getCode("https://www.glo-story.com/18-boys?p=", 17);
        pc.getCode("https://www.glo-story.com/3-women?p=",95);
        pc.getCode("https://www.glo-story.com/15-men?p=",78);
        pc.getCode("https://www.glo-story.com/17-girls?p=",31);

        // pc.getPrice("https://www.glo-story.com/18-boys?p=", 17);
        // pc.getPrice("https://www.glo-story.com/3-women?p=",95);
        //   pc.getPrice("https://www.glo-story.com/15-men?p=",78);
       //  pc.getPrice("https://www.glo-story.com/17-girls?p=",31);

       // pc.getPhoto("https://www.glo-story.com/18-boys?p=", 17);
       // pc.getPhoto("https://www.glo-story.com/3-women?p=",95);
       // pc.getPhoto("https://www.glo-story.com/15-men?p=",78);
      //  pc.getPhoto("https://www.glo-story.com/17-girls?p=",31);
    /*    List <Elements> picture = new ArrayList <>();
        Document document = Jsoup.connect("https://www.glo-story.com/18-boys?p=2").get();
        document.getElementsByClass("lazy").attr("style").toString();
        Elements elements = document.getElementsByClass("lazy");

      picture.add(elements);

             for (int j = 0; j < picture.size(); j++) {
            System.out.println(picture.size() + "" + picture.get(j).attr("style"));
             }*/
    }

    public void getCode(String link, int pageQuantity) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/codes.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
        // buff.write("Product code\tProduct type\tLanguage\tPrice\tDetailed image\n");
        List <WebElement> product = new ArrayList <>();
        int j = 0;
        // int k= 0;
        for (int i = 1; i < pageQuantity; i++) {
            // webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.get(link + i);
            product.addAll(webDriver.findElements(By.className("product-name")));
            // System.out.println(product.size()+" "+price.size()+" "+ photo.size());
            for (; j < product.size(); j++) {
                // for (; k < price.size(); k++) {
                //    for (; g < photo.size(); g++) {

                if (product.get(j).getText().equals("")) {
                    continue;
                }
                Pattern pattern = Pattern.compile("[A-Z]{3,4}-\\w{4,5}");
                Matcher matcher = pattern.matcher(product.get(j).getText());
                while (matcher.find()) {
                    buff.write(product.get(j).getText().substring(matcher.start(), matcher.end()) + "\n");
                    //  buff.write("\tP\tru\t" + price.get(k).getText().replaceAll("[^0-9,.]", "")+"\n");
                    // buff.write(product.get(j).getText().substring(matcher.start(), matcher.end()) + "\tP\tru\t" + price.get(k).getText().replaceAll("[^0-9,.]", "") + "\n");
                    count++;
                    System.out.println(count + " " + product.get(j).getText() + " " + product.get(j).getText().substring(matcher.start(), matcher.end()));
                    // System.out.println(count + " " + product.get(j).getText().substring(matcher.start(), matcher.end()) + " " + price.get(k).getText().replaceAll("[^0-9,.]", "")+"\n ");
                    //   System.out.println(count + " " + product.get(j).getText().substring(matcher.start(), matcher.end()) + " " + price.get(j).getText().replaceAll("[^0-9,.]", "")+" "+ photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$","")+"#{[ee]:;[en]:;[ru]:;}"+"\n");
                    //}
                }
            }
        }
        buff.flush();
        buff.close();
        csvWriter.close();
    }

    public void getPrice(String link, int pageQuantity) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        String url = ("https://www.glo-story.com/login?back=my-account");
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get(url);
        //Scanner scanner = new Scanner("/Users/lipsuke/Desktop/prices.csv");
        WebElement login = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/div[1]/input"));
        WebElement password = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/div[2]/span/input"));
        WebElement btn = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/form/div/p[2]/button"));
        login.sendKeys("lipsuke@gmail.com");
        password.sendKeys("Reaba1966");
        btn.click();
        int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/prices.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
        // buff.write("Product code\tProduct type\tLanguage\tPrice\tDetailed image\n");
        List <WebElement> price = new ArrayList <>();
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

            price.addAll(webDriver.findElements(By.className("content_price")));

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
        csvWriter.close();
    }

    public void getPhoto(String link, int pageQuantity) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/photos.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);
        // buff.write("Product code\tProduct type\tLanguage\tPrice\tDetailed image\n");
        List <WebElement> photo = new ArrayList <>();
        int j = 0;
        for (int i = 1; i < pageQuantity; i++) {
            webDriver.get(link + i);

            photo.addAll(webDriver.findElements(By.className("lazy")));
            // System.out.println( photo.size());
       // }

            for (; j < photo.size(); j++) {
                if (photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$", "").equals("")) {
                    continue;
                }
                 //Actions action = new Actions(webDriver);
               //  action.moveToElement(photo.get(j)).perform();

             //   if(photo.get(j).equals((WebElement el) -> action.moveToElement(photo.get(j)).perform())){

             //   }
               // action.perform();
               // this.sleep(2);
                if(photo.get(j).isDisplayed()) {
                    buff.write(photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$", "") + "\n");
                    //  buff.write("\tP\tru\t" + price.get(k).getText().replaceAll("[^0-9,.]", "")+"\n");
                    // buff.write(product.get(j).getText().substring(matcher.start(), matcher.end()) + "\tP\tru\t" + price.get(k).getText().replaceAll("[^0-9,.]", "") + "\n");
                    count++;
                    System.out.println(count + " " + photo.get(j).getAttribute("style").substring(23).replaceAll("[\");]+$", ""));
                }
           }
        }
        buff.flush();
        buff.close();
        csvWriter.close();

    }

    public void getPicture(String link, int pageQuantity) throws IOException {
        int count = 0;
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/pictures.csv", true);
        BufferedWriter buff = new BufferedWriter(csvWriter);

        List <Element> picture = new ArrayList <>();
        for (int i = 1; i < pageQuantity; i++) {
            Document doc = Jsoup.connect(link + i).get();
            Elements elements = doc.getElementsByClass("lazy");
            for (Element el : elements) {
                picture.add(el);
                buff.write(el.child(1).attr("style").substring(22).replaceAll("[\");]+$", "") + "\n");

            }
        }
        //  picture.addAll(elements);
        for (int j = 0; j < picture.size(); j++) {

           /*     if (picture.get(j).attr("style").substring(23).replaceAll("[\");]+$", "").equals("")) {
                    continue;
                }

                //   if(j%2==0){
                //     continue;
                //  }

                buff.write(picture.get(j).attr("style").substring(23).replaceAll("[\");]+$", "") + "\n");
                //  buff.write("\tP\tru\t" + price.get(k).getText().replaceAll("[^0-9,.]", "")+"\n");
                // buff.write(product.get(j).getText().substring(matcher.start(), matcher.end()) + "\tP\tru\t" + price.get(k).getText().replaceAll("[^0-9,.]", "") + "\n");
                count++;*/
            count++;
            System.out.println(count + " " + picture.get(j).attr("style").substring(22).replaceAll("[\");]+$", ""));
            // buff.write(picture.get(j).attr("style").substring(23).replaceAll("[\");]+$", "") + "\n");
        }
        // }
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
    public static void getCodes(List<String>code) {
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
    }
}
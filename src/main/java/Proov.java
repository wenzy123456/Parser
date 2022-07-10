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
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Proov {
 static    FileWriter csvWriter;

    static {
        try {
            csvWriter = new FileWriter("/Users/lipsuke/Desktop/links2.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static    BufferedWriter buff = new BufferedWriter(csvWriter);

    public Proov() throws IOException {
    }


    public static void main(String[] args) throws IOException {
        buff.write("Product code\tPair type\tThumbnail\tDetailed image\n");
        getLinks("https://www.glo-story.com/18-boys?p=",2,findCode("https://www.glo-story.com/18-boys?p=",2));
        buff.flush();
        buff.close();
        csvWriter.close();
    }
   public static void getLinks(String link, int pageQuantity,List<String>codes) throws IOException {
       System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
       for (int i = 1; i < pageQuantity; i++) {
           Document doc = Jsoup.connect(link + i).get();
           Elements links = doc.select("a[href]");
           Set <String> listLinks = new HashSet <>();
           int count = 1;
           for (int l=0;l<codes.size();l++) {
               for (Element el : links) {
                   if (el.attr("abs:href").contains(codes.get(l))) {
                       listLinks.add(el.attr("abs:href"));
                   }
               }
           }
           List <WebElement> firstphoto = new ArrayList <>();
           for (String s : listLinks) {
               ChromeDriver webDriver = new ChromeDriver();
               webDriver.get(s);
               firstphoto.addAll(webDriver.findElements(By.className("img-responsive")));
           }
           List <String> firstphototext = new ArrayList <>();
           for (WebElement el : firstphoto) {
               firstphototext.add(el.getAttribute("src"));
           }
           List <String> photo = firstphototext.stream().distinct().collect(Collectors.toList());
           for (int j = 0; j < photo.size(); j++) {
               if (photo.get(j).contains("thickbox")) {
                   if (j == 1) {
                       buff.write("\tM\t\t" + photo.get(j).replaceAll("[\");]+$", "") + "#{[ee]:;[en]:;[ru]:;}" + "\n");
                       System.out.println(j + " " + count++ + " " + photo.get(j));
                   } else {
                       System.out.println(j + " " + count++ + " " + photo.get(j));
                       buff.write("\tA\t\t" + photo.get(j).replaceAll("[\");]+$", "") + "#{[ee]:;[en]:;[ru]:;}" + "\n");
                   }
               } else continue;
           }
       }
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
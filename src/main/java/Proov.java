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


    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/links1.csv");
        BufferedWriter buff = new BufferedWriter(csvWriter);
        buff.write("Product code\tPair type\tThumbnail\tDetailed image\n");
        List<String> codes = new ArrayList<>();
        codes.add("7513");

        for(int i=1; i<14; i++) {
            Document doc = Jsoup.connect("https://www.glo-story.com/18-boys?p=" + i).get();
            Elements links = doc.select("a[href]");
            Set <String> listLinks = new HashSet <>();
            int count = 1;
            for (Element link : links) {
                if (link.attr("abs:href").contains("9199")) {
                    listLinks.add(link.attr("abs:href"));
                    // buff.write(String.valueOf(listLinks)) ;
                    //System.out.println(count++ + " " + link.attr("abs:href"));
                }

            }
           List <WebElement> firstphoto = new ArrayList <>() ;
            for (String s : listLinks) {
                // buff.write(s + "\n");
                ChromeDriver webDriver = new ChromeDriver();
                //   webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webDriver.get(s);
                //  webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                firstphoto.addAll(webDriver.findElements(By.className("img-responsive")));
              //  List <WebElement> photo = Arrays.asList (photos.stream().distinct());
            }
            List<String>firstphototext = new ArrayList<>();
            for (WebElement el:firstphoto)
                 {
                firstphototext.add(el.getAttribute("src"));
            }
            List<String>photo = firstphototext.stream().distinct().collect(Collectors.toList());
            for ( int j = 0; j < photo.size(); j++) {
                //++countlink;
              //  System.out.println(countlink);
                if (photo.get(j).contains("thickbox")) {
                   if (j == 1) {
                       buff.write("\tM\t\t" +photo.get(j).replaceAll("[\");]+$","")+"#{[ee]:;[en]:;[ru]:;}"+"\n");
                       System.out.println(j+" "+count++ + " " + photo.get(j));
                  } else {
                        System.out.println(j+" "+count++ + " " + photo.get(j));
                        buff.write("\tA\t\t" +photo.get(j).replaceAll("[\");]+$","")+"#{[ee]:;[en]:;[ru]:;}"+"\n");
                  }
                }
             else continue;
            }
        }

         buff.flush();
         buff.close();
         csvWriter.close();
    }
}
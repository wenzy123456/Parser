import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URL;

public class Parser {

    public static void main(String[] args) throws Exception {
     Elements page = getPage();
       System.out.println("PRIVET");
        System.setProperty("webdriver.chrome.driver","/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
      ChromeDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.glo-story.com/jackets/12979-Women-s-leather-jacket-5996525323579.html");
     /*  for (int i = 2; i <= 7; i++) {
            WebElement paginationBtn = webDriver.findElement(By.xpath("//*[@id=\"z0IHRoAjlM3\"]/ul/li[" + i + "]/a"));
            paginationBtn.click();
            Thread.sleep(1000);
            System.out.println(i);
        }
        Thread.sleep(1000);
        webDriver.quit();*/



       /*   int index = 0;
        Element tableWth = page.select("table[class=wt]").first();
        Elements names = tableWth.select("tr[class=wth]");
        Elements values = tableWth.select("tr[valign=top]");

      for(Element name: names){
            String dateString = name.select("th[id=dt]").text();
            String date = getDateFromString(dateString);
            System.out.println(date + "    Явления    Температура    Давление   Влажность    Ветер");
            int iterationCount = printPartValues(values, index);
            index +=iterationCount;
        }*/
    }
    public static Elements getPage() throws IOException{
       // String url = ("http://www.pogoda.spb.ru/");
        String url = ("https://www.glo-story.com/home/8077-women-s-leather-jacket-5996525267439.html?search_query=WPY&results=17");
        Document page = Jsoup.parse(new URL(url), 6000);
      Elements element = page.getElementsByClass("small");
      Element el = page.getElementById("quantity_wanted");
              {
        }
        return element;
    }

 /*   private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String getDateFromString(String stringDate) throws Exception{
        Matcher matcher =  pattern.matcher(stringDate);
        if (matcher.find()){
            return matcher.group();
        }
        throw new Exception("Can't extract date from string");
    }

    private static int printPartValues(Elements values, int index){
        int iterationCount = 4;
        if (index==0){
            Element valueLn = values.get(0);
            /**  boolean isMorning = valueLn.text().contains("Утро");
             if (isMorning){
             iterationCount = 3;
             }

            switch(valueLn.text().split(" ")[0]){
                case ("День"):
                    iterationCount = 3;
                    break;
                case ("Вечер"):
                    iterationCount = 2;
                    break;
                case ("Ночь"):
                    iterationCount = 1;
                    break;
            }

        }

        for(int i=0; i<iterationCount; i++){
            Element valueLine = values.get(index+i);
            for(Element td: valueLine.select("td")){
                System.out.print(td.text()+"    ");
            }
            System.out.println();
        }
        return(iterationCount);
    }*/
}

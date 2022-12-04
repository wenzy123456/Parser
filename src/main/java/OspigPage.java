import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.ConnectionClosedException;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static org.testng.AssertJUnit.assertEquals;


public class OspigPage {
   int i;
   int j;
   static int n ;

  // static int QuantityProduct = getQuantityProduct();

    public static void main(String[] args) throws Exception {
      //  Ospig.sleep(70);
     //  getDataPage(1, 1, QuantityProduct);
        OspigPage.getQuantityProduct();

    }
    public static List <String> getQuantityProduct(){
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.get(url);
        Ospig.sleep(2);
        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
        WebElement btn = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
        login.sendKeys("708145");
        password.sendKeys("Reaba1966");
        btn.click();
      WebElement login1 = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
        WebElement password1 = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
        WebElement btn1 = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
        login1.sendKeys("708145");
        password1.sendKeys("Reaba1966");
        btn1.click();
        Ospig.sleep(40);
        String url1 = ("https://b2b-shop.ospig.de/default.aspx#/search");
        webDriver.get(url1);
        Ospig.sleep(30);
        List <WebElement> product = new ArrayList <>();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        for (int l=0; l < 50; l++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Ospig.sleep(1);
        }
        product.addAll(webDriver.findElements(By.className("img")));
        System.out.println(product.size());
        List <String> codes= new ArrayList <>();
        for(int i=product.size(); i>0;i--) {
            StringBuilder sbDiv = new StringBuilder();
            sbDiv.append("//*[@id=\"scrollpane\"]/div/div[");
            sbDiv.append(i);
            sbDiv.append("]/span/div[2]/div[2]/div[1]/a");
            String sDivNumber = sbDiv.toString();
           // System.out.println(i+" "+webDriver.findElement(By.xpath(sDivNumber)).getText());
            codes.add(webDriver.findElement(By.xpath(sDivNumber)).getText());
        }
        getCodes(codes);
        webDriver.close();
        return  codes;
    }

    public static void getDataPage(int j, int i, int QuantityProduct) throws IOException, InterruptedException, NoSuchElementException {

        BufferedWriter buff = null;
        FileWriter csvWriter = null;
        ChromeDriver webDriver = null;
        try  {
            csvWriter = new FileWriter("/Users/lipsuke/Desktop/ospigsample.csv", true);
            buff = new BufferedWriter(csvWriter);
            System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
            String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
            webDriver = new ChromeDriver();
            webDriver.get(url);
            WebElement login = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
            WebElement password = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
            WebElement btn = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
            login.sendKeys("708145");
            password.sendKeys("Reaba1966");
            btn.click();
            WebElement login1 = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
            WebElement password1 = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
            WebElement btn1 = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
            login1.sendKeys("708145");
            password1.sendKeys("Reaba1966");
            btn1.click();
            Ospig.sleep(40);
            String url1 = ("https://b2b-shop.ospig.de/default.aspx#/search");
            webDriver.get(url1);
            Ospig.sleep(30);

            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            int  n = j ;
              //   n = n + 7;
          //  while (webDriver.findElement(By.xpath("//*[@id=\"scrollpane\"]/div/div[i]/span/div[1]/div[2]/img")).isDisplayed()){
          for ( j = 0; j < n; j++) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Ospig.sleep(1);
            }

            for ( ;  i <= QuantityProduct; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("#scrollpane > div > div:nth-child(");
                sb.append(i);
                sb.append(") > span > div.box-top > div.center-block > img");
                String s = sb.toString();

                WebElement el = webDriver.findElement(By.cssSelector(s));
                Ospig.sleep(2);
                Actions action = new Actions(webDriver);
                action.doubleClick(el).perform();
                Ospig.sleep(2);


               OspigPage.getPage(webDriver);

               WebElement code = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[2]/span"));
                WebElement products = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[3]/span"));
                WebElement material = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[4]/div[2]/div/span"));
                WebElement price = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[5]/div[2]/div[2]/div/span[1]/b"));
                WebElement picture = webDriver.findElement(By.id("product_image"));
                WebElement table = webDriver.findElement(By.className("ordertable"));

                List<WebElement> colors = new ArrayList<>();
                colors.addAll(webDriver.findElements(By.className("colorbutton")));

                Ospig.sleep(2);

                Pattern pattern = Pattern.compile("^[0-9]*[.,]?[0-9]{1,2}");
                Matcher matcher = pattern.matcher(price.getText());
                while (matcher.find()) {
                    String floatPrice = new DecimalFormat("#0.00").format(Float.parseFloat(price.getText().substring(matcher.start(), matcher.end()).replace(",", ".")) * 2);
                    buff.write(code.getText() + "\t " + products.getText() + "\t " + floatPrice + "\t " + picture.getAttribute("src") + "\n");
                }


                WebElement el1 = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[1]/span"));
                Actions action1 = new Actions(webDriver);
                action1.doubleClick(el1).perform();
                Ospig.sleep(2);
            }
    }
        catch (ConnectionClosedException ccex){
            System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
            String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
            webDriver = new ChromeDriver();
            webDriver.get(url);
            WebElement login = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
            WebElement password = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
            WebElement btn = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
            login.sendKeys("708145");
            password.sendKeys("Reaba1966");
            btn.submit();
            WebElement login1 = webDriver.findElement(By.xpath("//*[@id=\"UserEmail\"]"));
            WebElement password1 = webDriver.findElement(By.xpath("//*[@id=\"UserPass\"]"));
            WebElement btn1 = webDriver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
            login1.sendKeys("708145");
            password1.sendKeys("Reaba1966");
            btn1.click();
            Ospig.sleep(40);
            String url1 = ("https://b2b-shop.ospig.de/default.aspx#/search");
            webDriver.get(url1);
            Ospig.sleep(30);
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            int  n = j ;
            for ( j = 0; j < n; j++) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Ospig.sleep(1);
            }
        }
        catch (Exception ex) {
            System.out.println("opanki" + j + " ;" + i);
            j= j+5;
            if(j>=50){
                j=j-7;
            }
            System.out.println(j + " ; " + i +" ; "+QuantityProduct);
            buff.flush();
            buff.close();
           csvWriter.close();
           webDriver.quit();
            getDataPage(j,i,QuantityProduct);
        }
        buff.flush();
        buff.close();
        csvWriter.close();
        webDriver.close();
    }
    public static void getPage(WebDriver webDriver) throws Exception {
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/ospigpage.csv", true);
        BufferedWriter   buff = new BufferedWriter(csvWriter);
        List<WebElement> colors = new ArrayList <>();
        colors.addAll(webDriver.findElements(By.className("colorbutton")));
        System.out.println(colors.size());
        int countDiv = 0;
        for (int h=0; h< colors.size();h++){
            System.out.println(colors.get(h).getTagName());
            if(!colors.get(h).getTagName().equals("div")){
                countDiv++;
            }
        }

        for (int g=0; g< colors.size();g++
        ) {

            if ( g >= colors.size()-countDiv){
                continue;
            }
            Actions action1 = new Actions(webDriver);
            action1.doubleClick(colors.get(g)).perform();
            Ospig.sleep(3);
            WebElement code = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[2]/span"));
            WebElement products = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[3]/span"));
            WebElement material = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[4]/div[2]/div/span"));
            WebElement price = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[5]/div[2]/div[2]/div/span[1]/b"));
            WebElement picture = webDriver.findElement(By.id("product_image"));
            WebElement table = webDriver.findElement(By.className("ordertable"));
            List <WebElement> tables = webDriver.findElements(By.className("ordertable"));
            List<WebElement> photos = webDriver.findElements(By.className("img-responsive"));
            for (WebElement eltable : tables
            ) {

                WebElement color = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[7]/div[1]/span[2]"));
                List <WebElement> rows_table = eltable.findElements(By.tagName("tr"));
                List <WebElement> columns_row = rows_table.get(1).findElements(By.tagName("td"));
                LinkedList <WebElement> columns = new LinkedList(columns_row);
                List <WebElement> title_columns = eltable.findElements(By.tagName("th"));
                LinkedList <WebElement> titles = new LinkedList(title_columns);
                LinkedHashMap <String, String> map = new LinkedHashMap <>();

                System.out.println(titles.get(1).getText());
                System.out.println(code.getText());
                System.out.println(products.getText());
                System.out.println(material.getText());
                System.out.println(price.getText());
                System.out.println(color.getText());

                buff.write(code.getText() + "\t " +products.getText() + "\t " + material.getText() + "\t " + price.getText() + "\t " + color.getText() + "\n");

                for (WebElement elm :photos
                ) {
                    if(elm.getAttribute("src")==null){
                        continue;
                    }
                    System.out.println(elm.getAttribute("src"));
                    buff.write(elm.getAttribute("src")+ "\n");
                }

                for (int i = 0; i < columns.size(); i++) {
                    if (i == 0) {
                        continue;
                    }
                    int j = i;
                    map.put(titles.get(j + 1).getText(), columns.get(i).getText());
                    buff.write(titles.get(j + 1).getText()+" "+ columns.get(i).getText()+ "\n");
                }
                System.out.println(map);
            }
        }
        buff.flush();
        buff.close();
        csvWriter.close();
    }
    public static List<String> getCodes(List<String>code) {
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
       // Set<String> newfin = new LinkedHashSet<>();

        while (iterator.hasNext()) {
            fin.add( String.valueOf( iterator.next() ) );
        }
     /*   for (String el : code) {
            System.out.println( el );
        }
        System.out.println( "--------------" );*/
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < fin.size(); i++) {
            int count = 0;
            for (int j = 0; j < code.size(); j++) {
                if (code.get( j ).equals( fin.get( i ) )) {
                    map.put( j, String.valueOf( new StringBuilder( code.get( j ) ).append( "-" ).append( count++ )));
                }
            }
        }

        for (int j =0; j<code.size(); j++) {
            for (Map.Entry<Integer, String> item : map.entrySet()) {
                code.set( item.getKey(), item.getValue() );
            }
        }
        int x =0;
        for (String el : code) {
            ++x;
            System.out.println(x+" "+ el );
        }
        return code;
    }


}
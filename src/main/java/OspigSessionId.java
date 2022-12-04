import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.profiler.model.Profile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.SessionId;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OspigSessionId {


    public static void main(String[] args) throws Exception {
      // System.out.println(OspigPage.getQuantityProduct());
       OspigSessionId.turnPage(479,getWebDriver());
       // OspigSessionId.getProduct(getWebDriver());
       // OspigSessionId.getPage(424,));
    }

    public static ChromeDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        return webDriver;
    }

    public Set <Cookie> getCookies() throws IOException, InterruptedException, NoSuchElementException {
        String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
        ChromeDriver webDriver = getWebDriver();
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
        System.out.println(webDriver.getSessionId().toString());
        String url1 = ("https://b2b-shop.ospig.de/default.aspx#/search");
        webDriver.get(url1);
        Ospig.sleep(30);
        Set <Cookie> allCookies = webDriver.manage().getCookies();
        webDriver.close();
        return allCookies;
    }

    public static void getPage(int divNumber, ChromeDriver webDriver) throws Exception {
        // получение основных данных со страницы
        FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/ospigproov.csv",true );
        BufferedWriter buff = new BufferedWriter(csvWriter);
        FileWriter csvWriterPhoto = new FileWriter("/Users/lipsuke/Desktop/ospigphoto.csv",true );
        BufferedWriter buffPhoto = new BufferedWriter(csvWriterPhoto);
        if (divNumber == 1) {
            buff.write("Product code\tLanguage\tCategory\tProduct name\tЦвет\tРазмер\tQuantity\tDetailed image\tVariation group code\tDescription\tPrice\n");
            buffPhoto.write("Product code\tPair type\tDetailed image\n");
        }
        //Строим строку xPath по номеру div
        StringBuilder sbDiv = new StringBuilder();
        sbDiv.append("//*[@id=\"scrollpane\"]/div/div[");
        sbDiv.append(divNumber);
        sbDiv.append("]/span/div[1]/div[2]/img");
        String sDivNumber = sbDiv.toString();
      /*  JavascriptExecutor js = (JavascriptExecutor) webDriver;
        for ( int j = 0; j < 40; j++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Ospig.sleep(1);
        }*/
        // находим картинку по номеру div
        WebElement el = webDriver.findElement(By.xpath(sDivNumber));
        Ospig.sleep(3);
        Actions action = new Actions(webDriver);
        // кликаем на картинку по номеру div
        action.doubleClick(el).perform();
        Ospig.sleep(3);
        // записываем лист цветов товара
        List <WebElement> colors = new ArrayList <>();
        colors.addAll(webDriver.findElements(By.className("colorbutton")));
        System.out.println(colors.size());
        int countDiv = 0;
        //цикл  для вычисления количества тегов span
        for (int h = 0; h < colors.size(); h++) {
            System.out.println(colors.get(h).getTagName());
            if (!colors.get(h).getTagName().equals("div")) {
                countDiv++;
            }
        }

     // цикл перебора цветов
        int r = 0;
        for (int g = 0; g < colors.size(); g++) {
    //убираем ненужные теги
            if (g >= colors.size() - countDiv) {
                continue;
            }
            Actions action1 = new Actions(webDriver);
            // кликаем на цвет и собираем инфу о товаре
            action1.doubleClick(colors.get(g)).perform();
            Ospig.sleep(3);
            WebElement code = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[2]/span"));
            WebElement products = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[3]/span"));
            WebElement material = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[4]/div[2]/div/span"));
            WebElement price = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[5]/div[2]/div[2]/div/span[1]/b"));
            WebElement picture = webDriver.findElement(By.id("product_image"));
            WebElement table = webDriver.findElement(By.className("ordertable"));
            // записываем в лист все таблицы страницы
            List <WebElement> tables = webDriver.findElements(By.className("ordertable"));
            List <WebElement> photos = webDriver.findElements(By.className("img-responsive"));
            // перебираем строки всех таблиц
            for (WebElement eltable : tables) {

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

                //  buff.write(code.getText() + "\t " +products.getText() + "\t " + material.getText() + "\t " + price.getText() + "\t " + color.getText() + "\n");

                for (WebElement elm : photos
                ) {
                    if (elm.getAttribute("src") == null) {
                        continue;
                    }
                    System.out.println(elm.getAttribute("src"));
                    //    buff.write(elm.getAttribute("src")+ "\n");
                }

                for (int i = 0; i < columns.size(); i++) {
                    if (i == 0) {
                        continue;
                    }
                    int j = i;
                    if (columns.get(i).getText().equals("0") | columns.get(i).getText().equals("")) {
                        continue;
                    }
                    for (WebElement elm : photos) {
                        if (elm.getAttribute("src") == null) {
                            continue;
                        }
                        //System.out.println(elm.getAttribute("src"));
                        buffPhoto.write(code.getText() + r + "\tA\t " + elm.getAttribute("src") + "\n");
                    }
                    Pattern pattern = Pattern.compile("^[0-9]*[.,]?[0-9]{1,2}");
                    Matcher matcher = pattern.matcher(price.getText());
                    while (matcher.find()) {
                        String floatPrice = new DecimalFormat("#0.00").format(Float.parseFloat(price.getText().substring(matcher.start(), matcher.end()).replace(",", ".")) * 2.1);
                        map.put(titles.get(j + 1).getText(), columns.get(i).getText());
                        //  if(photos.get(0).getAttribute("src").equals(null)){
                        //     photos.get(0).getAttribute("src").toString()="";
                        // }
                        if(photos.get(0).getAttribute("src")==null){
                            photos.get(0).getAttribute("src").equals("");
                        }


                        if(titles.get(1).getText().contains("Länge:")) {
                            buff.write(code.getText() + r + "\tru\tOspig\t" + products.getText() + "\t " + color.getText() + "\t " + titles.get(j + 1).getText() + "/" + titles.get(1).getText().substring(6).trim() + "\t " + columns.get(i).getText() + "\t" + photos.get(0).getAttribute("src") + "\t" + code.getText().replaceAll("\\s+", "") + "\t" + material.getText() + "\t" + floatPrice + "\n");
                            r++;
                        }
                     else    buff.write(code.getText() + r + "\tru\tOspig\t" + products.getText() + "\t " + color.getText() + "\t " + titles.get(j + 1).getText() + "\t " + columns.get(i).getText() + "\t" + photos.get(0).getAttribute("src") + "\t" + code.getText().replaceAll("\\s+", "") + "\t" + material.getText() + "\t" + floatPrice + "\n");
                        r++;
                    }
                }
              //  System.out.println(map);
            }

        }
        buffPhoto.flush();
        buffPhoto.close();
        csvWriterPhoto.close();
        buff.flush();
        buff.close();
        csvWriter.close();
        WebElement el1 = webDriver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[1]/span"));
        Actions action1 = new Actions(webDriver);
        action1.doubleClick(el1).perform();
       //  webDriver.quit();
    }

    public static void turnPage(int w,ChromeDriver webDriver) throws Exception {
        //ChromeDriver webDriver = getWebDriver();
        int i = 0;
        try {
          //  List <WebElement> product = getProduct(webDriver);
          //  findProduct(w, webDriver);
           // int size = OspigPage.getQuantityProduct();
           getMainPage(webDriver);
            findProduct(w,webDriver);
            for (i = w; i <= 586;  i++) {
                getPage(i, webDriver);
                Ospig.sleep(1);
            }
        } catch (Exception exception) {
            getMainPage(webDriver);
            findProduct(i,webDriver);
            turnPage(i,webDriver);
        }
        webDriver.quit();
    }


public static void findProduct(int i, ChromeDriver webDriver){
        int n =0;
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    if (i==0){
        n=0;
    }
    if( i>=1 & i <=100) {
        n =5;
    }
    if( i> 100 & i <=250) {
        n =20;
    }
    if( i> 250 & i <=400) {
        n =35;
    }
    if( i> 400 & i <=600) {
        n =40;
    }
    if(i>600){
        n=45;
    }

        for (int j = 0; j < n; j++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Ospig.sleep(1);
        }
}
public static List<WebElement> getProduct(ChromeDriver webDriver) throws IOException {
    FileWriter csvWriter = new FileWriter("/Users/lipsuke/Desktop/paddocks.csv");
    BufferedWriter buff = new BufferedWriter(csvWriter);
    LinkedHashMap <String, String> map = new LinkedHashMap <>();
    buff.write("Product code\tSecondary categories\n");
        //ChromeDriver webDriver = getWebDriver();
    // System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
    String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
    // ChromeDriver webDriver = new ChromeDriver();
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
    WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"side-menu\"]/ul/li[1]/span"));
    webElement.click();
    List <WebElement> categories = new ArrayList <>();
    for (int z = 2; z < 5; z++) {
        if (z==9){
            continue;
        }
        if (z==11){
            continue;
        }
        StringBuilder sbDiv = new StringBuilder();
        sbDiv.append("//*[@id=\"side-menu\"]/ul/li[1]/ul/li[");
        sbDiv.append(z);
        sbDiv.append("]/span");
        String sDivNumber = sbDiv.toString();
        categories.add(webDriver.findElement(By.xpath(sDivNumber)));
        WebElement elc = webDriver.findElement(By.xpath(sDivNumber));
        elc.click();
        // webDriver.findElement(By.xpath(sDivNumber).click();
        //}
        //  for (WebElement el : categories) {
        //      System.out.println(el.getText());
        //  }

        List <WebElement> product = new ArrayList <>();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        for (int l = 0; l < 50; l++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Ospig.sleep(1);
        }
        product.addAll(webDriver.findElements(By.className("img")));
        System.out.println(product.size());
        List <WebElement> code = new ArrayList <>();
        for (int i = 0; i < product.size(); i++) {
            int j = i;
            StringBuilder sbDiv1 = new StringBuilder();
            sbDiv1.append("//*[@id=\"scrollpane\"]/div/div[");
            sbDiv1.append(j + 1);
            sbDiv1.append("]/span/div[2]/div[2]/div[1]/a\n");
            String sDivNumber1 = sbDiv1.toString();
            code.add(webDriver.findElement(By.xpath(sDivNumber1)));
           // if(z<5) {
                map.put(code.get(i).getText() + "0", "Ospig///" + webElement.getText() + "///" + elc.getText());
          //  }
          //  if(z>=5){
          //     String value = map.get(code.get(i).getText() + "0")+ "Ospig///" + webElement.getText() + "///" + elc.getText();
           //    map.put(code.get(i).getText() + "0",value);
          //  }
           System.out.println(i + "\t" + code.get(i).getText() + "0" + "\t" + webElement.getText() + "///"+elc.getText());
            buff.write(code.get(i).getText()+"0" +"\t"+"Ospig///"+ webElement.getText()+"///"+elc.getText()+"\n");
        }
    }

    for (Map.Entry entry: map.entrySet()) {
        System.out.println(entry);
        buff.write(entry+"\n");
    }
    buff.flush();
    buff.close();
    csvWriter.close();
    webDriver.quit();
    return categories;

}
public static void getMainPage(ChromeDriver webDriver){
    String url = ("https://b2b-shop.ospig.de/login.aspx?ReturnUrl=%2flogout.aspx");
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
}

}
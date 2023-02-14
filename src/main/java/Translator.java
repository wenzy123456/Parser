import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.ArrayList;

public class Translator {
    int i;
    int count;

    public static void main(String[] args) throws Exception {
        translate(383,0);
    }


    public static void translate(int i,int count) throws Exception {
        ArrayList <String> myList = new ArrayList<String>(); // массив
        FileReader myFile = null;
        BufferedReader buff = null;
        FileWriter myFile3 = null;
        BufferedWriter buff3 = null;
        myFile3 = new FileWriter( "/Users/lipsuke/Desktop/readyen.csv",true );
        buff3 = new BufferedWriter( myFile3 );

        FileWriter myFil = null;
        BufferedWriter buf = null;
        myFil = new FileWriter( "/Users/lipsuke/Desktop/indexes.csv",true );
        buf = new BufferedWriter( myFil );


        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
      // webDriver.get("https://google.ee");
       // Actions action = new Actions(webDriver);
       // WebElement btn = webDriver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div"));
       // action.doubleClick(btn).perform();
        //перевод с русского
       // webDriver.get("https://translate.google.com/?hl=ru&sl=ru&tl=et&op=translate");
        //перевод с английского
        webDriver.get("https://translate.google.com/?hl=ru&sl=en&tl=et&op=translate");
       Actions action1 = new Actions(webDriver);
       WebElement btn1 = webDriver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/div/div[2]/div[1]/div[3]/div[1]/div[1]/form[2]/div/div/button/span"));
       action1.doubleClick(btn1).perform();
        try {

            myFile = new FileReader("/Users/lipsuke/Desktop/entrans.csv");
            buff = new BufferedReader(myFile);
            Ospig.sleep(2);
            while (true){
                String line = buff.readLine();
                if(line==null)
                    break;
                System.out.println(myList.size());
                myList.add(line + "\n");
            }
           for ( ; i< myList.size();i++) {
               //перевод с русского
              // webDriver.get("https://translate.google.com/?hl=ru&sl=ru&tl=et&op=translate");
               //перевод с английского
               webDriver.get("https://translate.google.com/?hl=ru&sl=en&tl=et&op=translate");
               WebElement text = webDriver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[2]/div[3]/c-wiz[1]/span/span/div/textarea"));
               text.sendKeys(myList.get(i) + "\n");
               Ospig.sleep(4);
               WebElement translate = webDriver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[2]/div[3]/c-wiz[2]/div/div[8]/div/div[1]/span[1]/span/span"));
               Ospig.sleep(4);
               System.out.println(translate.getText());
               // buff3.write( "PRIVET" + "\n" );
               buff3.write(translate.getText() + "\n");

            }
        } catch (Exception e) {
            count++;
            int  k=i;
            if (count%2==0){
                k= i+1;
            }
            buf.write("i="+i +"k="+k+"count="+count+ "\n");
            buff.close();
            myFile.close();
            buff3.flush();
            buff3.close();
            myFile3.close();
            buf.close();;
            myFil.close();
            webDriver.quit();
            translate(k,count);

        }
         finally {
                buff.close();
                myFile.close();
                buff3.flush();
                buff3.close();
                myFile3.close();
                buf.close();;
                myFil.close();
                webDriver.quit();
            }
        }
    }

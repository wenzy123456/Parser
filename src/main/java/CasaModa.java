import org.openqa.selenium.chrome.ChromeDriver;

public class CasaModa {
    public static void main(String[] args) throws Exception {
        String url = "https://b2b.casamoda.com/de/en/";
        CasaModa.getWebDriver().get(url);

    }

    public static ChromeDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/lipsuke/Downloads/Parser/.idea/selenium/chromedriver");
        ChromeDriver webDriver = new ChromeDriver();
        return webDriver;
    }
}

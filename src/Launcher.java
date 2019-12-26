import Amazon.AmazonExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Launcher {
public static WebDriver driver=null;
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        AmazonExecutor.Perform(driver);
        //open web page
        driver.close();
    }
}

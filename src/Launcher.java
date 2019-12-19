import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Launcher {
public static WebDriver driver=null;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");

        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //open web page
        driver.navigate().to("https://www.amazon.in");
        driver.manage().window().maximize();
        String title=driver.getTitle();

        if(title.contains("Amazon.in")) {
            System.out.println("Title matched");
        }
        else {
            System.out.println(String.format("Actually the title is %s", title));
        }
        Actions action = new Actions(driver);
        action.moveToElement(element).click().perform();

    }
}

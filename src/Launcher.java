import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.xml.bind.Element;
import java.util.List;
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
        //WebElement element1=
                driver.findElement(By.cssSelector("#nav-hamburger-menu")).click();
        //WebElement element1=

                driver.findElement(By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible > li:nth-child(2) > a")).click();
                //driver.findElement(By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible.hmenu-translateX > li:nth-child(3) > a")).click();
               // driver.findElement(By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible.hmenu-translateX > li:nth-child(4) > a")).click();
            //       driver.findElement(By.linkText("Echo Dot"));
                  WebElement element1= driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[3]/a"));
                  element1.sendKeys(Keys.CONTROL +"t");




        //action.moveToElement(element).click().perform();

    }
}

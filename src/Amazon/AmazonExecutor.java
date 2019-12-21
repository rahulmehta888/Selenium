package Amazon;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.Scanner;

public class AmazonExecutor {
   private static File logfile=new File(String.format(".\\data\\amazon\\temp\\Log%s.txt",String.valueOf(System.currentTimeMillis())));

   private static FileWriter log;

    static {
        try {
            log = new FileWriter(logfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AmazonExecutor() throws IOException {

    }

    public static void Perform(WebDriver driver) throws IOException {
        driver.navigate().to("https://www.amazon.in");
       // driver.manage().window().maximize();
        AmazonFileReader amazonFileReader=new AmazonFileReader();
        XSSFWorkbook instructions = amazonFileReader.getinstructions();

        XSSFSheet currentsheet = instructions.getSheet("Sheet1");
        
        int TotalsRows = currentsheet.getLastRowNum() - currentsheet.getFirstRowNum();

        //Fetching Values one row at a time
        {
            for(int i=currentsheet.getFirstRowNum()+1;i<currentsheet.getLastRowNum()+1;i++)
            {
                XSSFRow row = currentsheet.getRow(i);

                String ObjectName=row.getCell(0).getStringCellValue();
                String ObjectFinder=row.getCell(1).getStringCellValue();
                String Action=row.getCell(2).getStringCellValue();

                log.write(String.format("Action Number %d", i));
                log.write("\n");

                if (Action.toLowerCase().matches("click")) {
                        driver.findElement(By.cssSelector(ObjectFinder)).click();
                        log.write(String.format("Action: %s Complete on %s", Action, ObjectName));
                        log.write("\n");
                    }

                else if (Action.toLowerCase().matches("sendkeys")) {
                    String Value=row.getCell(3).getStringCellValue();

                    driver.findElement(By.cssSelector(ObjectFinder)).sendKeys(Value);
                   // driver.findElement( By.xpath("//*[@id=\"nav-search\"]/form/div[3]/div[1]"));
                    log.write(String.format("Action: %s Complete on %s", Action, ObjectName));
                    log.write("\n");
                }

                else if (Action.toLowerCase().matches("linkclick")) {
                    String Value=row.getCell(3).getStringCellValue();

                    driver.findElement(By.cssSelector(ObjectFinder)).sendKeys(Value);
                    log.write(String.format("Action: %s Complete on %s", Action, ObjectName));
                    log.write("\n");
                }
                else if (Action.toLowerCase().matches(null)) {
                    log.write("---------------------------------------------");
                    log.write("\n");
                }
                else{
                    log.write("Warning: Not a Valid entry");
                    log.write("\n");

                }
            }
        }


    }
}

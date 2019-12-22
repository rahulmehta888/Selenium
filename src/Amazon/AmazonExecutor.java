package Amazon;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AmazonExecutor {
   private static File logfile=new File(String.format(".\\data\\amazon\\temp\\Log%s.txt",String.valueOf(System.currentTimeMillis())));

   //logger instantiation
   private static  PrintStream log;

      static {
        try {
            log = new PrintStream(logfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      }

        public static void Perform(WebDriver driver) throws IOException {
        driver.navigate().to("https://www.amazon.in");
        driver.manage().window().maximize();

        AmazonFileReader amazonFileReader=new AmazonFileReader();
            /**Fetching xlsx file reader

             */
        XSSFWorkbook instructions = amazonFileReader.getinstructions();

        XSSFSheet currentsheet = instructions.getSheet("Sheet1");
        
        int TotalsRows = currentsheet.getLastRowNum() - currentsheet.getFirstRowNum();

            /**Fetching Values one row at a time
             *
             */

            for(int i=currentsheet.getFirstRowNum()+1;i<currentsheet.getLastRowNum()+1;i++)
            {

                /**Reading each row. Performing actions on value specified inAction column
                 *
                 */

                XSSFRow row = currentsheet.getRow(i);
                System.out.println(i);
                log.println(String.format("Action Number %d", i));
                log.println(System.lineSeparator());
                try {
                    String Action1 = row.getCell(2).getStringCellValue().toLowerCase();
                    if (row.getCell(2).getStringCellValue().toLowerCase().matches("click")) {
                        driver.findElement(By.cssSelector(row.getCell(1).getStringCellValue())).click();
                        log.println(String.format("Action: %s Complete on %s", row.getCell(2).getStringCellValue(), row.getCell(0).getStringCellValue()));
                        log.println(System.lineSeparator());

                        AmazonExecutor.CloseExtraTab(driver);
                    }
                    else if (row.getCell(2).getStringCellValue().toLowerCase().matches("idclick")) {

                        driver.findElement(By.id(row.getCell(1).getStringCellValue())).click();
                        log.println(String.format("Action: %s Complete on %s", row.getCell(2).getStringCellValue(), row.getCell(0).getStringCellValue()));
                        log.println(System.lineSeparator());

                        AmazonExecutor.CloseExtraTab(driver);
                    }

                    else if (row.getCell(2).getStringCellValue().toLowerCase().matches("sendkeys")) {
                        String Value=row.getCell(3).getStringCellValue();

                        driver.findElement(By.cssSelector(row.getCell(1).getStringCellValue())).sendKeys(Value);
                        // driver.findElement( By.xpath("//*[@id=\"nav-search\"]/form/div[3]/div[1]"));
                        log.println(String.format("Action: %s Complete on %s", row.getCell(2).getStringCellValue(), row.getCell(0).getStringCellValue()));
                        log.println(System.lineSeparator());
                    }

                    else if (row.getCell(2).getStringCellValue().toLowerCase().matches("linkclick")) {
                        //String Value=row.getCell(3).getStringCellValue();

                        System.out.println("Yes");
                        driver.findElement(By.linkText(row.getCell(1).getStringCellValue())).click();

                        log.println(String.format("Action: %s Complete on %s", row.getCell(2).getStringCellValue(), row.getCell(0).getStringCellValue()));
                        log.println(System.lineSeparator());
                        AmazonExecutor.CloseExtraTab(driver);
                    }

                    else{
                        log.println("Warning: Not a Valid entry");
                        log.println(System.lineSeparator());
                        //System.out.println("Yes");
                    }
                }
                /** Since Null is a valid entry, handling it separately.

                 */
              catch (NullPointerException e)
                {
                   // e.getCause().toString();
                   //System.err.println("NullPointerException: " +  e.getMessage());
                    log.println("------------------Empty Field moving on to next one---------------------------");
                    log.println(System.lineSeparator());
                    //driver.navigate().to("https://www.amazon.in");
                }


            }

    }

    private static void CloseExtraTab(WebDriver driver) {
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        if (tabs.size()>1) {

            driver.switchTo().window((String) tabs.get(0)).close();
            tabs = new ArrayList(driver.getWindowHandles());
            driver.switchTo().window((String) tabs.get(0));
        }
    }
}

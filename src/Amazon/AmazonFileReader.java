package Amazon;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AmazonFileReader {

    File filefile=new File(".\\data\\amazon\\instructions.xlsx");
    FileInputStream file=new FileInputStream(filefile);
    XSSFWorkbook instructions=new XSSFWorkbook(file);


    public AmazonFileReader() throws IOException
    {

    }

    public XSSFWorkbook getinstructions(){
        return instructions;

    }
}

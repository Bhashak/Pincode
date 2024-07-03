package Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import gherkin.lexer.Th;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public  class PincodeThree {
    public static String pincode = "";
    static WebDriver driver;
    @Test
    public void Test1() throws InterruptedException, IOException {

        WebDriverManager.edgedriver().setup();
        driver=new EdgeDriver();
        String pincode="";
        driver.manage().window().maximize();

        FileInputStream fileInputStream=new FileInputStream("C:\\DQG\\DQG_WEB\\src\\main\\java\\Resources\\Book3k.xlsx");
        Workbook workbook=new XSSFWorkbook(fileInputStream);
        Sheet sheet=workbook.getSheetAt(0);



        for(int i=0;i<sheet.getLastRowNum();i++)
        {
            Row row=sheet.getRow(i);
            String continent=row.getCell(0).getStringCellValue();
            String Country=row.getCell(1).getStringCellValue();
            String State=row.getCell(2).getStringCellValue();
            String city=row.getCell(3).getStringCellValue();


            driver.get("https://worldpostalcode.com/");
//            WebElement search=driver.findElement(By.xpath("//input[@id='search']"));
           WebElement scrollToMap=driver.findElement(By.xpath("//div[@id='map_canvas']"));
           WebElement pincodetext=driver.findElement(By.xpath(""));
            try {
                Thread.sleep(2000);
                driver.findElement(By.xpath("//input[@id='search']")).sendKeys(continent+","+Country+","+State+","+city,Keys.ENTER);
                System.out.println("entered");
                scrolltoEle(scrollToMap);
               pincode=pincodetext.getText();
                System.out.println(pincode);
            }catch (NoSuchElementException exception)
            {
                pincode="Not Found";
            }

            Cell cell=row.createCell(4,CellType.STRING);
            cell.setCellValue("name");
        }

        FileOutputStream outputStream=new FileOutputStream("C:\\DQG\\DQG_WEB\\src\\main\\java\\Resources\\Book3k.xlsx");
       workbook.write(outputStream);
       workbook.close();
       outputStream.close();

    }
    public static void scrolltoEle(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public static void refreshBrowser(WebDriver driver) {
        driver.navigate().refresh();
    }
}
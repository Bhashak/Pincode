package Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public class pincode {
    public static String pincode = "";
    static WebDriver driver;

    @Test
    public void pincode() throws InterruptedException, IOException {
      WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        FileInputStream excelFile = new FileInputStream(".//src//main//java//Resources//poincode.xlsx");
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
//            String country = row.getCell(0).getStringCellValue();
            String state = row.getCell(0).getStringCellValue();
            String city = row.getCell(1).getStringCellValue();

            driver.get("https://www.bing.com/");

            driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(    state + "," + city + " Common pincode", Keys.ENTER);
            Thread.sleep(2000);

            try {
                WebElement pincodeElement = driver.findElement(By.xpath("//div[@class='b_focusTextLarge' or @class='b_focusTextMedium']"));
                pincode = pincodeElement.getText();//div[@class='b_focusTextLarge' or @class='b_focusTextMedium']
              System.out.println(pincode);
            } catch (NoSuchElementException e) {
            System.out.println("First element not found at Row Number: " + rowNum + ", trying next element.");
                try {
                    WebElement element1 = driver.findElement(By.xpath("//p[@class='b_lineclamp3 b_algoSlug']//strong[1]"));
                    if (element1.isDisplayed()) {

                        try {
                             pincode = element1.getText();
                            int number = Integer.parseInt(pincode);
                             // Output: 123
                        } catch (NumberFormatException e1) {
                            pincode="Not Found";
                        }
                        System.out.println(pincode);
                    }
                } catch (NoSuchElementException e1) {
                    System.out.println("Second element not found at Row Number: " + rowNum + ", trying next element.");
                    try {
                        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/h2/a"));
                        WebElement element3 = driver.findElement(By.xpath("//span[@style='font-size:250%; font-weight:bold;']"));
                        if (element2.isDisplayed()) {
//                            JavascriptExecutor js = (JavascriptExecutor) driver;
//                            js.executeScript("arguments[0].removeAttribute('target');", element2);
//                            element2.click();
//                            Thread.sleep(2000);
//                            pincode=element3.getText();
//                            System.out.println(pincode);
//                            driver.navigate().back();
                        }
                    } catch (NoSuchElementException e2) {
                        System.out.println("All elements not found at Row Number: " + rowNum + ", setting pincode as 'Not Found'.");
                        pincode = "Not Found";
                    }
                }
            }

            Cell pincodeCell = row.createCell(2, CellType.STRING);  // Column index 7 corresponds to the 8th column
            pincodeCell.setCellValue(pincode);
        }

        FileOutputStream outputStream = new FileOutputStream(".//src//main//java//Resources//poincode.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        excelFile.close();
        driver.quit();
    }

    public static void scrolltoEle(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
    }
}

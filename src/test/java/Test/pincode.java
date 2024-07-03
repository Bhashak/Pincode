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
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public  class pincode {
    public static String pincode = "";
    static WebDriver driver;
    @Test
    public void Test1() throws InterruptedException, IOException {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https:///www.google.com");
        FileInputStream excelFile = new FileInputStream("C:\\DQG\\DQG_WEB\\src\\main\\java\\Resources\\Book1k.xlsx");
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            String continent = row.getCell(0).getStringCellValue();
            String country = row.getCell(1).getStringCellValue();
            String state = row.getCell(2).getStringCellValue();
            String city = row.getCell(3).getStringCellValue();

            driver.get("https://www.google.com");

//            WebElement search=  driver.findElement(By.xpath("//textarea[@name='q']"));
//            WebElement search=driver.findElement(By.cssSelector("#APjFqb"));
//            if(!search.isDisplayed())
//            {
//                refreshBrowser(driver);
//            }
            try {
                WebElement search=driver.findElement(By.cssSelector("#APjFqb"));
                search.sendKeys(    continent+ "," +country+ "," +state + "," + city + " pincode", Keys.ENTER);

            }catch (NoSuchElementException noe)
            {
                driver.navigate().refresh();
            }


            try {
//                WebElement pincodeElement = driver.findElement(By.xpath("//div[@class='b_focusTextLarge' or @class='b_focusTextMedium']"));
                WebElement pincodeElement = driver.findElement(By.xpath("//div[@class='bVj5Zb FozYP'][1]"));

                pincode = pincodeElement.getText();//div[@class='b_focusTextLarge' or @class='b_focusTextMedium']
                System.out.println(pincode);
            } catch (NoSuchElementException e) {
                System.out.println("First element not found at Row Number: " + rowNum + ", trying next element.");
                try {
//                    WebElement element1 = driver.findElement(By.xpath("//p[@class='b_lineclamp3 b_algoSlug']//strong[1]"));
                    WebElement element1 = driver.findElement(By.xpath("//div[@class='HwtpBd gsrt PZPZlf kTOYnf']//div[@class='Z0LcW t2b5Cf']"));

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
                        WebElement elemzip = driver.findElement(By.xpath("//a[@class='FLP8od']"));
                        pincode=elemzip.getText();
                        System.out.println(pincode);

                    } catch (NoSuchElementException p1) {
                        System.out.println("Not Found");
                        pincode="Not Found";
                        try{
                            WebElement ee=driver.findElement(By.xpath("//span//em[1]"));
                            if (ee.isDisplayed()) {

                                try {
                                    pincode = ee.getText();
                                    int number = Integer.parseInt(pincode);
                                    // Output: 123
                                } catch (NumberFormatException e11) {
                                    pincode="Not Found";
                                }
                                System.out.println(pincode);
                            }
                        }catch (NoSuchElementException ee0) {
                            pincode = "Not Found";
                            try {
                                WebElement element2 = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/block-component/div/div[1]/div/div/div/div/div[1]/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[3]/b"));
                                WebElement element3 = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/block-component/div/div[1]/div/div/div/div/div[1]/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[2]"));
                                if (element3.getText().equalsIgnoreCase(city)) {

                                    pincode = element2.getText();
                                    System.out.println(pincode);

                                }
                            } catch (NoSuchElementException e2) {
                                System.out.println("All elements not found at Row Number: " + rowNum + ", setting pincode as 'Not Found'.");
                                pincode = "Not Found";
                            }
                        }
                    }
                }
            }

            Cell pincodeCell = row.createCell(4, CellType.STRING);  // Column index 7 corresponds to the 8th column
            pincodeCell.setCellValue(pincode);
        }

        FileOutputStream outputStream = new FileOutputStream("C:\\DQG\\DQG_WEB\\src\\main\\java\\Resources\\Book1k.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        excelFile.close();
        driver.quit();
    }

    public static void scrolltoEle(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public static void refreshBrowser(WebDriver driver) {
        driver.navigate().refresh();
    }
}
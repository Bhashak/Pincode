package Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PincodeSearch {
    public static String pincode = "";
    static Workbook workbook;
    static Sheet sheet;

    public static void main(String[] args) throws InterruptedException, IOException {
        FileInputStream excelFile = new FileInputStream(".//src//main//java//Resources//poincode.xlsx");
        workbook = new XSSFWorkbook(excelFile);
        sheet = workbook.getSheetAt(0);
        excelFile.close();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            executor.execute(new PincodeTask(rowNum));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);

        try (FileOutputStream outputStream = new FileOutputStream(".//src//main//java//Resources//poincode.xlsx")) {
            workbook.write(outputStream);
        }
        workbook.close();
    }

    static class PincodeTask implements Runnable {
        int rowNum;
        WebDriver driver;

        PincodeTask(int rowNum) {
            this.rowNum = rowNum;
            WebDriverManager.edgedriver().setup();
            this.driver = new EdgeDriver();
            this.driver.manage().window().maximize();
        }

        @Override
        public void run() {
            try {
                Row row = sheet.getRow(rowNum);
                String state = row.getCell(0).getStringCellValue();
                String city = row.getCell(1).getStringCellValue();

                driver.get("https://www.bing.com/");
                driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(state + "," + city + " Common pincode", Keys.ENTER);
                Thread.sleep(2000);

                String pincode = getPincode();
                synchronized (sheet) {
                    Cell pincodeCell = row.createCell(2, CellType.STRING);
                    pincodeCell.setCellValue(pincode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                driver.quit();
            }
        }

        private String getPincode() {
            try {
                WebElement pincodeElement = driver.findElement(By.xpath("//div[@class='b_focusTextLarge' or @class='b_focusTextMedium']"));
                return pincodeElement.getText();
            } catch (NoSuchElementException e) {
                try {
                    WebElement element1 = driver.findElement(By.xpath("//p[@class='b_lineclamp3 b_algoSlug']//strong[1]"));
                    if (element1.isDisplayed()) {
                        try {
                            String pincode = element1.getText();
                            Integer.parseInt(pincode);
                            return pincode;
                        } catch (NumberFormatException e1) {
                            return "Not Found";
                        }
                    }
                } catch (NoSuchElementException e1) {
                    try {
                        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/h2/a"));
                        if (element2.isDisplayed()) {
                            // If necessary, further implementation for clicking and getting the pincode
                        }
                    } catch (NoSuchElementException e2) {
                        return "Not Found";
                    }
                }
            }
            return "Not Found";
        }
    }
}

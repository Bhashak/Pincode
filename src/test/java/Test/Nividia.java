package Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Nividia {
    static WebDriver driver;

    public Nividia(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void main(String[] args) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.nvidia.com/gtc/session-catalog/#/");

        Nividia nividia = new Nividia(driver);
        select_Industry();
        speaker_Details();
        driver.quit(); // Close the browser once done
    }

    @FindBy(xpath = "//span[text()='Industry']")
    private static WebElement industry;

    @FindBy(xpath = "//span[text()='Agriculture']")
    private static WebElement Telecom_industry;

    @FindBy(xpath = "//div[@class='title-text']//parent::a[@class='collapsed']")
    private static List<WebElement> sessions;

    @FindBy(css = ".ot-sdk-row #onetrust-accept-btn-handler")
    private static WebElement AcceptCookies;

    @FindBy(xpath = "//button[text()='Allow']")
    private static WebElement Notification_allow;

    @FindBy(xpath = "//div[@data-test='speakers-component']//span")
    private static List<WebElement> speakerName;

    @FindBy(xpath = "//div[@class='speakers']//span[1]")
    private static List<WebElement> speakerRole;

    @FindBy(xpath = "//div[@class='speakers']//span[2]")
    private static List<WebElement> speakerCity;

    public static void select_Industry() {
        AcceptCookies.click();
        waitToLoad();
        industry.click();
        Telecom_industry.click();
        industry.click();
    }

    public static void speaker_Details() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Speaker Details");

        // Write headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Speaker Name");
        headerRow.createCell(1).setCellValue("Speaker Role");
        headerRow.createCell(2).setCellValue("Speaker City");

        // Iterate through sessions and write speaker details to Excel
        for (int i = 0; i < sessions.size(); i++) {

            waitToLoad();
            scrolltoSession(sessions.get(i));
            WebElement element=sessions.get(i);
            System.out.println("Element is :"+element.getText());
//            WebDriverWait webDriverWait=new WebDriverWait(driver,Duration.ofSeconds(20));
//            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.valueOf(element))));
//            Actions actions=new Actions(driver);
//            actions.moveToElement(element).click().build().perform();
//            actions.click(element).perform();
            waitToLoad();
            String speaker = speakerName.get(i).getText();
            String role = speakerRole.get(i).getText();
            String city = speakerCity.get(i).getText();
            writeSpeakerToExcel(sheet, i + 1, speaker, role, city); // i+1 to start from the second row (after header row)
        }

        // Write workbook to file
        try (FileOutputStream outputStream = new FileOutputStream("SpeakerDetails.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeSpeakerToExcel(Sheet sheet, int rowNumber, String speakerName, String speakerRole, String speakerCity) {
        Row row = sheet.createRow(rowNumber);
        row.createCell(0).setCellValue(speakerName);
        row.createCell(1).setCellValue(speakerRole);
        row.createCell(2).setCellValue(speakerCity);
    }

    public static void scrolltoSession(WebElement ele) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", ele);
    }

    public static void waitToLoad() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

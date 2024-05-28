package Pom_Pages;

import Base_Package.DriverUtility;
import Enums.Checks;
import Enums.Connection_data;
import Enums.Credentials;
import gherkin.lexer.Th;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Random;

public class DQ_Rules_Page {



    WebDriver driver;
    public DriverUtility driverUtility=new DriverUtility(driver);

    public DQ_Rules_Page(WebDriver driver)
    {

        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void ScrolltoElement(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to the element using JavaScript
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll to the top of the page
        js.executeScript("window.scrollTo(0, 0)");
    }
    public void dragandDrop(WebElement sourceElement,WebElement targetElement) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).build().perform();

        // Optional: Verify the result
//        String textAfterDrop = targetElement.getText();
//        if (textAfterDrop.equals("Dropped!")) {
//            System.out.println("Drag and drop successful!");
//        } else {
//            System.out.println("Drag and drop failed.");
//        }

    }
    @FindBy(xpath = "//input[@name='ruleName']") private WebElement Rulename;



    public void performActionBasedOnCheck(Checks check,WebElement element2) {
        switch (check) {
            case No_Empty_Null_Value:
                String No_Empty_Null_Valu_check = "//span[text()='" + Checks.No_Empty_Null_Value.getCheck() + "']";
                WebElement element1 = driver.findElement(By.xpath(No_Empty_Null_Valu_check));
                dragandDrop(element1, element2);

                break;
            case STARTS_WITH:
                String STARTS_WITH_check = "//span[text()='" + Checks.STARTS_WITH.getCheck() + "']";
                WebElement STARTS_WITH_element = driver.findElement(By.xpath(STARTS_WITH_check));
                dragandDrop(STARTS_WITH_element, element2);
                System.out.println("Handling Starts with");
                // Additional actions for STARTS_WITH
                break;
            case ENDS_WITH:
                System.out.println("Handling Ends with");
                // Additional actions for ENDS_WITH
                break;
            case CONTAINS:
                System.out.println("Handling Contains");
                // Additional actions for CONTAINS
                break;
            // Add cases for other enum values as needed
            default:
                throw new IllegalStateException("Unexpected value: " + check);
        }
    }


    public void enterTextBasedOnCheck(Checks check) {
        switch (check) {
            case ENDS_WITH:
                String ENDS_WITH_check = "//p[text()='"+Checks.ENDS_WITH.getCheck()+"']//ancestor::div[@class=\"space-between MuiBox-root css-8atqhb\"]//following-sibling::div//input[@placeholder=\"enter text here\"]";
                WebElement element1 = driver.findElement(By.xpath(ENDS_WITH_check));
                element1.sendKeys("a");

                break;
            case STARTS_WITH:
                String STARTS_WITH_check = "//p[text()='"+Checks.STARTS_WITH.getCheck()+"']//ancestor::div[@class=\"space-between MuiBox-root css-8atqhb\"]//following-sibling::div//input[@placeholder=\"enter text here\"]";
                WebElement STARTS_WITH_element = driver.findElement(By.xpath(STARTS_WITH_check));
                STARTS_WITH_element.sendKeys("1");
                System.out.println("Handling Starts with");
                // Additional actions for STARTS_WITH
                break;
            case DUPLICATE_CHECK:
                System.out.println("Handling Ends with");
                // Additional actions for ENDS_WITH
                break;
            case CONTAINS:
                System.out.println("Handling Contains");
                // Additional actions for CONTAINS
                break;
            // Add cases for other enum values as needed
            default:
                throw new IllegalStateException("Unexpected value: " + check);
        }
    }
    @FindBy(xpath = "//div[text()='Drop here']") private  WebElement drop;


    public void Table_Validation() throws InterruptedException {
//       Thread.sleep(6000);
        Rulename.sendKeys(Connection_data.SQL_CONNECTIOIN_NAME.getConnectionData()+Checks.Rulename.getCheck());
        Thread.sleep(3000);
        performActionBasedOnCheck(Checks.No_Empty_Null_Value,drop);
    }

    public void Snowflake_Table_Validation() throws InterruptedException {
//       Thread.sleep(6000);
        Rulename.sendKeys(Connection_data.Connection_name.getConnectionData()+Checks.Rulename.getCheck());
        Thread.sleep(3000);
        performActionBasedOnCheck(Checks.No_Empty_Null_Value,drop);
     Thread.sleep(3000);
    }
    @FindBy(css = "[class*=' MuiTypography-body1 h']") public WebElement Addcolumns;
    @FindBy(xpath = "//tbody[@class='MuiTableBody-root css-1xnox0e'] //input[@type='checkbox']") public WebElement Column1;
    @FindBy(xpath = "//div[@class='MuiBox-root css-tzofp1']//button") private WebElement Cancelbtncol;
    public void AddColumnsandChecks() throws InterruptedException {

        Addcolumns.click();
        Thread.sleep(1000);
        Column1.click();
        Cancelbtncol.click();
    }

    @FindBy(xpath = "//button[text()='Preview']") public WebElement ValPreview;
    @FindBy(xpath = "//div[@role='dialog']//button[@type='button'][1]") private WebElement Valcancelpreview;
    @FindBy(xpath = "//button[text()='Create Rule']") public WebElement Createrule;
    public void Preview_and_CreateRule() throws IOException, InterruptedException {

        String filepath="C:\\DQG\\DQG_WEB\\target\\Screenshots\\DQ_Rulepage\\_"+"Table_Validation_Preview"+driverUtility.getSystemdate()+".png";

        Thread.sleep(3000);
        ScrolltoElement(ValPreview);
        ValPreview.click();
        Thread.sleep(10000);
        driverUtility.takeScreenshot(driver,filepath);
        Valcancelpreview.click();
        Createrule.click();
        Thread.sleep(4000);
    }
    @FindBy(xpath ="//button[@aria-label='Execute'][1]" ) private  WebElement ExecuteBtn;
    @FindBy(xpath = "//button[@aria-label='Results'][1]") private  WebElement Resultsbtn;
    @FindBy(xpath = "//button[@value='grid']") private  WebElement Gridview;
   @FindBy(xpath = "//button[@variant='contained']") private WebElement execlist;
    @FindBy(xpath = "//div[contains(@class,'MuiPaper-rounded MuiPaper-elevation1 DQ')]") private  WebElement execution_Results;
    @FindBy(xpath = "//div[@class='innerHeader MuiBox-root css-0']") private WebElement exelistDrop;
    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container css-v3z1wi'] //button") private WebElement execlstCancelBtn;
    @FindBy(xpath = "//div[@row-index='0'] //input") private  WebElement firstcheckbox;
    @FindBy(xpath = "//div[@aria-label='Delete']") private  WebElement delete;
    @FindBy(xpath = "//button[text()='Yes']") private  WebElement yesbtn;


    @FindBy(xpath = "//button[@aria-label='Edit'][1]") private WebElement edit;
    @FindBy(xpath = "//button[text()='Add Row']") private WebElement Add_Row;
    @FindBy(xpath = "//button[text()='Update']") private  WebElement UpdateBtn;
    public void Edit_AddCheck_Update() throws InterruptedException {
     edit.click();
     Thread.sleep(5000);
//     Add_Row.click();
     performActionBasedOnCheck(Checks.STARTS_WITH,drop);
     enterTextBasedOnCheck(Checks.STARTS_WITH);
     Thread.sleep(3000);
     ScrolltoElement(UpdateBtn);
     UpdateBtn.click();

    }
    public void Execution_Results() throws IOException, InterruptedException {


        Thread.sleep(4000);
        ExecuteBtn.click();
        dragandDrop(execlist,exelistDrop);
        execlstCancelBtn.click();
        Thread.sleep(10000);

        Resultsbtn.click();
        Thread.sleep(4000);
        Gridview.click();
        execution_Results.click();
        Thread.sleep(4000);
        String filepath="C:\\DQG\\DQG_WEB\\target\\Screenshots\\Execution_Results\\_"+driverUtility.getSystemdate()+".png";
        driverUtility.takeScreenshot(driver,filepath);
       scrollToTop();
       Thread.sleep(3000);
        firstcheckbox.click();
        delete.click();
        yesbtn.click();
        Thread.sleep(4000);
    }
}

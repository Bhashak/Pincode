package Pom_Pages;

import Base_Package.DriverUtility;
import Enums.Connection_data;
import gherkin.lexer.Th;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;
import java.io.IOException;

public class Comparision_Page {
    WebDriver driver;

    private DriverUtility driverUtility=new DriverUtility(driver);
    public Comparision_Page(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//span[text()='DQ Rules']") private  WebElement dqRules;
    @FindBy(xpath = "//button[text()='Create DQ Rule']")private  WebElement CreateDQRule;
    @FindBy(xpath = "//img[@alt='Comparison']")private  WebElement ComparisionIMG;
    @FindBy(xpath = "//input[@role='combobox'][1]") private  WebElement select1stConn;
    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-md-6 css-1lj7jth'][2] //input[@role='combobox']") private  WebElement select2ndConn;
    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-6 css-rpybyc'][2]//input[@role='combobox']") private WebElement secondconntab;
    @FindBy(xpath = "//button[text()='Next']")private  WebElement NextBtn;
    @FindBy(xpath = "//button[text()='GO']") private  WebElement GoBtn1;
    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-6 css-rpybyc'][2]//button[text()='GO']") private  WebElement GoBtn2;
    @FindBy(xpath = "//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 MuiTableContainer-root css-14qyj2i']//th[2]") private  WebElement Drop;


    public void dragandDrop(WebElement sourceElement,WebElement targetElement) {

        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).build().perform();
    }
    public void scroll(int axis) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll to the top of the page
        js.executeScript("window.scrollTo(0, "+axis+")");
    }

    public void scroll_and_drop(WebElement dragele, WebElement intermediatele ,WebElement dropele)
    {

        Actions actions = new Actions(driver);
//        actions.doubleClick(dragele);
        System.out.println("dragged");
        actions.clickAndHold(dragele)
                .moveToElement(dragele)
                .pause(1000) // Pause to simulate scroll (if needed)
                .release(dropele)
                .build()
                .perform();
    }
    public void Comparision() throws InterruptedException {
        Thread.sleep(3000);
        dqRules.click();
        Thread.sleep(3000);
        CreateDQRule.click();
        Thread.sleep(3000);
        ComparisionIMG.click();
        Thread.sleep(3000);
        select1stConn.click();
        driverUtility.downKey();
        Thread.sleep(3000);
        select2ndConn.click();
        driverUtility.downKey();
        NextBtn.click();
        Thread.sleep(5000);

//        secondconntab.click();
//        driverUtility.downKey();
//        GoBtn2.click();

    }


    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-136foal'][1]") private WebElement firsttablecolumn;

    @FindBy(xpath = "//span[text()='Column Mapping']")private  WebElement interele;
    public void firstconnection() throws InterruptedException {
        Thread.sleep(3000);
        select1stConn.click();
        driverUtility.downKey();
        GoBtn1.click();
        Thread.sleep(6000);
        scroll(280);
        Thread.sleep(3000);
//        scroll_and_drop(firsttablecolumn,interele,Drop);
        firsttablecolumn.click();
        dragandDrop(firsttablecolumn,Drop);
        scroll(0);

    }
    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-6 css-rpybyc'][2]//p[@class='MuiTypography-root MuiTypography-body1 css-136foal'][1]") private  WebElement Secondcon1stcolumn;
    public void Secondconnection() throws InterruptedException {

        Thread.sleep(3000);
        secondconntab.click();
        driverUtility.downKey();
        GoBtn2.click();
        Thread.sleep(6000);
        scroll(280);
        Thread.sleep(3000);
//        scroll_and_drop(firsttablecolumn,interele,Drop);
        Secondcon1stcolumn.click();
        dragandDrop(Secondcon1stcolumn,Drop);

    }
    @FindBy(xpath = "//button[text()='Add Data Quality Checks']") private WebElement AddDQRules;
    @FindBy(xpath = "//button[text()='Preview']")private  WebElement preview;
    @FindBy(xpath = "//div[contains(@class,'MuiDialogTitle-root dialogTitle  css-11i7hlm')]//button")private  WebElement previewcancelBtn;

    @FindBy(xpath = "//button[text()='Create DQ Rule']")private  WebElement createRule;
    @FindBy(xpath = "//input[@name='rulename']")private  WebElement enterRulename;
    @FindBy(xpath = "//button[text()='Create']")private WebElement create;
    public void Preview_and_CreateDQRule() throws IOException, InterruptedException {

        AddDQRules.click();
        preview.click();
        Thread.sleep(6000);
        String filepath="C:\\DQG\\DQG_WEB\\target\\Screenshots\\Comparision\\Preview\\_"+"Comparisioin_preview"+driverUtility.getSystemdate()+".png";
        driverUtility.takeScreenshot(driver,filepath);
        previewcancelBtn.click();
        createRule.click();
        enterRulename.sendKeys(Connection_data.Comaprision.getConnectionData());
        create.click();
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


    public void execute() throws InterruptedException, IOException {
        Thread.sleep(3000);
        ExecuteBtn.click();
        dragandDrop(execlist,exelistDrop);
        execlstCancelBtn.click();
        Thread.sleep(10000);
        Resultsbtn.click();
        Thread.sleep(4000);
        Gridview.click();
        execution_Results.click();
        Thread.sleep(4000);
        String filepath="C:\\DQG\\DQG_WEB\\target\\Screenshots\\Comparision\\Results\\_"+"Comparisioin_Results"+driverUtility.getSystemdate()+".png";
        driverUtility.takeScreenshot(driver,filepath);
        scroll(0);
        Thread.sleep(3000);
        firstcheckbox.click();
        delete.click();
        yesbtn.click();
        Thread.sleep(4000);
    }


}

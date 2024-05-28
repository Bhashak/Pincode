package Pom_Pages;

import Base_Package.DriverUtility;
import Enums.Connection_data;
import gherkin.lexer.Th;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class Connections_Page {

    WebDriver driver;
    public DriverUtility driverUtility=new DriverUtility(driver);
    public Connections_Page(WebDriver driver)
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
    //Snowfalke ,Postgress and SQL common Paths
    //change Xpath connection xpath for other connections
    @FindBy(xpath = "//div[@aria-label='Connections']") private WebElement Connections;
    @FindBy(xpath = "//div[@class='pt74 MuiBox-root css-0']//img[@src='/assets/uploads/snowflake.png']") public WebElement SnowflakeConnection;
    @FindBy(xpath = "//div[@class='pt74 MuiBox-root css-0']//img[@src='/assets/uploads/sql.png']")public  WebElement SQLConnection;
    @FindBy(xpath = "//button[text()='Add new']") public WebElement AddConnection ;
    @FindBy(xpath = "//label[text()='Connection Name']/../..//input") public WebElement ConnectionName;
    @FindBy(xpath = "//input[@name='server']") public WebElement Server ;
    @FindBy(xpath = "//input[@name='dataBase']") public WebElement Database;
    @FindBy(xpath = "//input[@name='user']") public WebElement User;
    @FindBy(xpath = "//input[@name='password']") public WebElement Password;
    @FindBy(xpath = "//span[text()='Change']") public WebElement ChangeBtn;
    @FindBy(xpath = "//input[@name='schema']") public WebElement ClickonSchema;
    @FindBy(xpath = "//li[@data-value='TEST']") public WebElement SelectTestSchema;
    @FindBy(xpath = "//button[text()='Test']") public WebElement CreateConnection;

    @FindBy(xpath = "//div[@class='MuiBox-root css-1kvffza'] //button[@type='button']") public WebElement cancelbtn;
    @FindBy(xpath = "//*[@col-id='connectionName'] //*[contains(@class,'MuiTypography-root MuiTypography-o')]") public List<WebElement>  ConncetionameValidation;
    @FindBy(xpath = "//button[@aria-label='Preview']") public List<WebElement> Preview;

    @FindBy(xpath = "//div[@class='ag-selection-checkbox']") public  List<WebElement> lstcheckboxes;
    @FindBy(xpath = "//div[@class='MuiBox-root css-2v37i8']") public WebElement previewvalidation;
    @FindBy(xpath ="//div[@class='previewConnection MuiBox-root css-0'] //button[@type='button']") public  WebElement PreviewCancelbtn;
    @FindBy(xpath = "//button[@aria-label='Edit']") public List<WebElement> Edit;
    @FindBy(xpath = "//div[@class='MuiBox-root css-vpsc0c'] //button[text()='Cancel']") public WebElement editCancelBtn;


    @FindBy(xpath = "//div[@class='v-center prevConTableItem MuiBox-root css-0'][1]") public  WebElement Clickon1stTable;
    @FindBy(xpath = "//div[@class='v-center prevConTableItem MuiBox-root css-0'][3]") public  WebElement Clickon3rdTable;
    @FindBy(xpath = "//input[@name='schema']")private  WebElement Schema;
    @FindBy(xpath = "//li[@data-value='PUBLIC']")private  WebElement publicschema;
    @FindBy(xpath = "//button[text()='Validate']") public  WebElement validate;
    public void Add_Snowflake_Connection() throws InterruptedException {
        Thread.sleep(5000);
        Connections.click();
        SnowflakeConnection.click();
        AddConnection.click();
        Thread.sleep(5000);
//            ConnectionName.click();
//

        Server.sendKeys(Connection_data.Server.getConnectionData());
        ConnectionName.click();
        ConnectionName.sendKeys(Connection_data.Connection_name.getConnectionData());
        Database.sendKeys(Connection_data.Database.getConnectionData());
        User.sendKeys(Connection_data.User.getConnectionData());
        Password.sendKeys(Connection_data.Password.getConnectionData());
//        Schema.click();
//        Thread.sleep(3000);
//        publicschema.click();
//        ChangeBtn.click();
//            ClickonSchema.click();
//            SelectTestSchema.click();
        CreateConnection.click();
        Thread.sleep(5000);

        cancelbtn.click();


        Thread.sleep(5000);
        Boolean value=false;

        for(int i=0;i<ConncetionameValidation.size();i++)
        {
            String strName=ConncetionameValidation.get(i).getText();

            if(strName.equalsIgnoreCase(Connection_data.Connection_name.getConnectionData())){

                System.out.println(ConncetionameValidation.get(i).getText());

                Edit.get(i).click();
                Thread.sleep(4000);
                driverUtility.assertionEquals(ConncetionameValidation.get(i).getText(),Connection_data.Connection_name.getConnectionData());
                editCancelBtn.click();

//                lstcheckboxes.get(i).click();
                Preview.get(i).click();
                Thread.sleep(4000);
                driverUtility.isDisplay(previewvalidation);
//                PreviewCancelbtn.click();
//                Clickon3rdTable.click();
                value=true;
                break;

            }
        }
        Assert.assertTrue(value);

    }
    public void Add_SQL_Connection() throws InterruptedException {
        Thread.sleep(5000);
        Connections.click();
        Thread.sleep(3000);
        SQLConnection.click();
        AddConnection.click();
        Thread.sleep(3000);
//            ConnectionName.click();
//

        ConnectionName.click();
        ConnectionName.sendKeys(Connection_data.SQL_CONNECTIOIN_NAME.getConnectionData());
        Server.sendKeys(Connection_data.SQL_SERVER.getConnectionData());
        Database.sendKeys(Connection_data.SQL_DATABASE.getConnectionData());
        User.sendKeys(Connection_data.SQL_USER.getConnectionData());
        Password.sendKeys(Connection_data.SQL_PASSWORD.getConnectionData());
//        ChangeBtn.click();
//            ClickonSchema.click();
//            SelectTestSchema.click();
        CreateConnection.click();
        Thread.sleep(3000);
        cancelbtn.click();
    }

        public void Edit_Preview_Validation() throws InterruptedException {
       Thread.sleep(5000);
        Boolean value=false;

        for(int i=0;i<ConncetionameValidation.size();i++)
        {
            String strName=ConncetionameValidation.get(i).getText();

            if(strName.equalsIgnoreCase(Connection_data.SQL_CONNECTIOIN_NAME.getConnectionData())){

                System.out.println(ConncetionameValidation.get(i).getText());

                Edit.get(i).click();
               Thread.sleep(4000);
                driverUtility.assertionEquals(ConncetionameValidation.get(i).getText(),Connection_data.SQL_CONNECTIOIN_NAME.getConnectionData());
                editCancelBtn.click();

//                lstcheckboxes.get(i).click();
                Preview.get(i).click();
                Thread.sleep(4000);
                driverUtility.isDisplay(previewvalidation);
//                PreviewCancelbtn.click();
//                Clickon1stTable.click();
                value=true;
                break;

            }
        }
        Assert.assertTrue(value);
    }
    //@FindBy(xpath = "")

    public void ValidateConnection() throws IOException, InterruptedException {
        Clickon1stTable.click();
        Thread.sleep(4000);
        String filepath=".//target//Screenshots//SQL//_"+Connection_data.SQL_CONNECTIOIN_NAME.getConnectionData()+driverUtility.getSystemdate()+".png";
        driverUtility.takeScreenshot(driver,filepath);
        validate.click();
        Thread.sleep(4000);
    }

    public void ValidateSnowflakeConnection() throws IOException, InterruptedException {

        Clickon1stTable.click();
        Thread.sleep(10000);
        String filepath=".//target//Screenshots//Snowflake//_"+Connection_data.Connection_name.getConnectionData()+driverUtility.getSystemdate()+".png";
        driverUtility.takeScreenshot(driver,filepath);
        validate.click();
        Thread.sleep(4000);
    }
    }





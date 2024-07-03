package Base_Package;

import Enums.Browser;
import Pom_Pages.Comparision_Page;
import Pom_Pages.Connections_Page;
import Pom_Pages.DQ_Rules_Page;
import Pom_Pages.Loginpage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(ListenerImplementation.class)
public class BaseClass {

    public static WebDriver driver;
    public static WebDriver sdriver;
    public  Loginpage   loginpage;
    public Connections_Page connectionsPage;
    public Comparision_Page comparisionPage;

    public  DQ_Rules_Page dqRulesPage;
    @BeforeClass
    public void launchBrowser() throws Throwable{


        switch(Browser.browser.getdata()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver= new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            default:
                System.out.println("error");
        }
        sdriver=driver;
        driver.manage().window().maximize();
        driver.get(Browser.Url.getdata());

        loginpage=new Loginpage(driver);
        loginpage.login();
    }

    @BeforeMethod
    public void loginIntoDQG() throws Throwable
    {

        connectionsPage=new Connections_Page(driver);
        dqRulesPage=new DQ_Rules_Page(driver);
        comparisionPage=new Comparision_Page(driver);



    }
    @AfterClass
    public void closeDQGTool()
    {

//		driver.close();
    }
}


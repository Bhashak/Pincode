package Pom_Pages;

import Base_Package.BaseClass;
import Enums.Credentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {


WebDriver driver;

    public Loginpage(WebDriver driver) {

        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@name='email']") public WebElement Username;
    @FindBy(xpath = "//input[@name='password']") public WebElement Password;
    @FindBy(xpath = "//button[@type='submit']") public WebElement SignInBtn;
    @FindBy(xpath = "//button[text()='Confirm']") public  WebElement Confirmbtn;





    public  void login() throws InterruptedException {

        Username.sendKeys(Credentials.USERNAME.getCredentials());
        Password.sendKeys(Credentials.PASSWORD.getCredentials());

        SignInBtn.click();
        Thread.sleep(5000);
       Confirmbtn.click();

    }
}

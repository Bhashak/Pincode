package Base_Package;

import Base_Package.DriverUtility;
import Base_Package.LogUtility;
import Enums.Browser;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;

import static Base_Package.BaseClass.driver;

/**
 * This class acts like an implementation class to override all the methods
 * present in ITestListener interface
 *
 */
public class ListenerImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;
	DriverUtility driverUtility = new DriverUtility(driver);
	LogUtility logUtility = new LogUtility();
	String extentreport = ".//target//Reports//Extent_Reports//Extent_Report" + driverUtility.getSystemdate() + ".html";

	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName); // test is created which will initialize the individual test execution
		Reporter.log(methodName + " => test script execution started", true);
	}


	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName + " ---> passed");
		Reporter.log(methodName + " => is Passed", true);
		sendEmail("DQG Automation Execution Report", "Hi Team,<br><br>DQG Automation Execution Completed at : " + driverUtility.getSystemdate() + "<br><br>Thanks & Regards,<br>Bhasha");
	}


	public void onTestFailure(ITestResult result) {
		String msg = result.getThrowable().toString();
		String methodName = result.getMethod().getMethodName();
		String screenShotName = ".//target//Reports//Failed_Reports//" + methodName + "-" + driverUtility.getSystemdate();

		test.log(Status.FAIL, methodName + "---> Failed");
		test.log(Status.FAIL, result.getThrowable());
		Reporter.log(methodName + " => is failed because => " + msg, true);

		try {
			String path = driverUtility.takeScreenshot(driver, screenShotName);
			test.addScreenCaptureFromPath(path); // Navigate to screenshot path and attach it to the report
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		sendEmail("DQG Automation Execution Failed", "Hi Team,<br><br>DQG Automation Execution Report done on: " + driverUtility.getSystemdate() + "<br><br>Regards,<br>Basha", screenShotName);
	}

	public void onTestSkipped(ITestResult result) {
		String msg = result.getThrowable().toString();
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName + " ---> Skipped");
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(methodName + " => is skipped because => " + msg, true);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// Not implemented
	}

	public void onStart(ITestContext context) {
		System.out.println("Started");
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(extentreport);
		htmlReport.config().setDocumentTitle("DQG Execution Report");
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setReportName("DQG Execution Report");

		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base-Browser", Browser.browser.getdata());
		report.setSystemInfo("Base-URL", Browser.Url.getdata());
		report.setSystemInfo("Reporter Name", "Basha");
	}


	public void onFinish(ITestContext context) {


		sendEmail("DQG Automation Execution Report", "Hi Team,<br><br>DQG Automation Execution Completed at : " + driverUtility.getSystemdate() + "<br><br>Thanks & Regards,<br>Bhasha",extentreport);

		report.flush(); // Consolidate all the test script execution and dump the status into report
	}

	private void sendEmail(String subject, String messageBody) {
		sendEmail(subject, messageBody, null);
	}

	private void sendEmail(String subject, String messageBody, String attachmentPath) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587); // Use 587 for TLS
			email.setAuthenticator(new DefaultAuthenticator("kmbb4578@gmail.com", "ntto lwsv rfwa plhk")); // Use App password
			email.setStartTLSEnabled(true); // Enable TLS
			email.setFrom("kmbb4578@gmail.com");
			email.setSubject(subject);
			email.setHtmlMsg(messageBody);

			// Add CC recipients
			email.addCc("bhasha.k@kairostech.com");

			// Attach the Extent Report if attachmentPath is not null
			if (attachmentPath != null) {
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(attachmentPath); // Path to the attachment file
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setDescription("Attachments");
				attachment.setName(new File(attachmentPath).getName());
				email.attach(attachment);
			}

			// Send the email
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}

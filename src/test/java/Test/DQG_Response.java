package Test;

import java.io.IOException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class DQG_Response {

    public int login_status;
    public int connectionList_status;
    public String login_responsebody;
    public String connectionList_responsebody;

    public static String pincode = "";
    static WebDriver driver;

    @Test
    public void loginAndConnectionList() throws EmailException, IOException, InterruptedException {
        HttpResponse<String> loginResponse = Unirest.post("https://kairostech.ai/DQG-AuthNZ/api/Login/Userlogin")
                .header("Content-Type", "application/json")
                .body("{\r\n" +
                        "    \"EncryptedData\": \"py_AauTREySmbudklrv2sbLRl4e_2xHui1TbvoLsB2VJbCmIirkhK6sAIy0VhcnO0dZUbhVgapcgRlnmbvoAbJbl7AUzxK9opfLT6fDPz3k=\"\r\n" +
                        "}")
                .asString();

        login_status = loginResponse.getStatus();
        login_responsebody = loginResponse.getBody();

        System.out.println("Login Status = " + login_status);
        System.out.println("Login Body = " + login_responsebody);

        HttpResponse<String> connectionListResponse = Unirest.post("https://kairostech.ai/DQG-Server/api/data-source/list")
                .header("Content-Type", "application/json")
                .body("{\"key\": \"\"}")
                .asString();

        connectionList_status = connectionListResponse.getStatus();
        connectionList_responsebody = connectionListResponse.getBody();
        System.out.println("email Not sent");
        System.out.println("Connections List Status = " + connectionList_status);
        System.out.println("Connections List Body = " + connectionList_responsebody);

        if (login_status != 200 || connectionList_status != 200) {
            sendErrorEmail(login_status, connectionList_status);
        }
    }

    private void sendErrorEmail(int loginStatus, int connectionListStatus) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("kmbb4578@gmail.com", "ntto lwsv rfwa plhk"));
        email.setSSLOnConnect(true);

        email.setFrom("kmbb4578@gmail.com");
        email.setSubject("DQG Server is not working");
        email.setHtmlMsg("Hi Team,<br>DQG Working Status:<br>" +
                "Login status: " + loginStatus + "<br>" +
                "Connections List Status: " + connectionListStatus);

        email.addCc("lakshminarayana.g@kairostech.com");
        email.addCc("bhasha.k@kairostech.com");
        email.addTo("ravikumar.p@kairostech.com");

        email.send();
        System.out.println("email sent");
    }
}

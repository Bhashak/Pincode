package Enums;

public enum Credentials {
    USERNAME("pranathi.g@kairostech.com"),
    PASSWORD("Password@1"),
    CONNECTIONS("Snowflake");

    String Credentials;
    Credentials(String Credentials) {
        this.Credentials=Credentials;
    }
     public  String getCredentials()
     {
         return  Credentials;
     }
}

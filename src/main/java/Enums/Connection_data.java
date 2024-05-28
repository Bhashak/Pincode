package Enums;

public enum Connection_data {

    Connection_name("Snowflake_Test"),
    SQL_CONNECTIOIN_NAME("SQL_TEST"),
    SQL_SERVER("20.59.29.222"),
    Comaprision("Comparision_Test"),



    SQL_DATABASE("TDM"),
    SQL_USER("Lakshmi"),
    SQL_PASSWORD("Password@1"),
    Server("ok43344.central-india.azure"),
    Database("KAIROS"),
    User("Mbbasha"),
    Password("Password@1");
    String data;
     Connection_data(String data){
        this.data=data;

    }

    public String getConnectionData()
    {
        return data;
    }

}

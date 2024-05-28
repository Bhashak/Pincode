package Enums;

public enum Browser {

    Url("https://dqg.kairostech.com/"),
    browser("edge");
    String data;
    Browser(String data)
    {
        this.data=data;
    }

    public String getdata()
    {
        return data;
    }
}

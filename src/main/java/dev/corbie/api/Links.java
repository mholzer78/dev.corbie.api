package dev.corbie.api;

public class Links {
    private String url;
    private String text;
    private String desc;
    private String domain = "https://api.corbie.dev";

    public Links(String url, String text, String desc) {
        this.url = domain + url;
        this.text = (text.equals("")) ? this.url : text;
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }
    public String getText() {
        return text;
    }
    public String getDesc() {
        return desc;
    }
}
package utils;

import java.util.Date;

public class Message {

    private String m = ""; // email
    private String i = ""; // icon
    private String n = ""; // username
    private String c = ""; // color
    private String t = ""; // text
    private long s = 0; // timestamp
    private String _key = "";

    public Message() {}

    public Message(String text, String email, String username, String icon, String color) {
        s = new Date().getTime();
        t = validateText(text);
        m = email;
        i = icon;
        n = username;
        c = color;
    }

    private String validateText(String text) {
        String result = text.trim().replaceAll("\\n", " ").replaceAll(" +", " ");
        if(result.length() > 300) result = result.substring(0, 300).trim() + "...";
        return result;
    }

    public String getM() {
        return m;
    }

    public String getI() {
        return i;
    }

    public String getN() {
        return n;
    }

    public String getC() {
        return c;
    }

    public String getT() {
        return t;
    }

    public long getS() {
        return s;
    }

    public String _getKey() {
        return _key;
    }

    public void _setKey(String key) {
        _key = key;
    }

    @Override
    public String toString() {
        return "Message" + (_key.equals("") ? "" : "#" + _key) + "{" + n + " says: " + t + "}";
    }
}
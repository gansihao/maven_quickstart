package main.spring.textrecognition.util;

public enum ContentTypeEnum {

    FORM("application/x-www-form-urlencoded"),

    JSON("application/json"),

    XML("text/xml");

    private String value;

    ContentTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

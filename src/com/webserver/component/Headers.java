package com.webserver.component;

import java.util.HashMap;
import java.util.Locale;

public class Headers extends HashMap<String, Header> {

    public Headers() {
        super();
    }

    public void setHeader(String key, String value) {
        put(key.toLowerCase(), new Header(key, value));
    }

    public String getHeader(String key) {
        return get(key.toLowerCase()).getValue();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Header header : values()) {
            str.append(header.getCaseSavedKey()).append(": ").append(header.getValue()).append("\n");
        }
        return str.toString();
    }
}

class Header {
    private String caseSavedKey;

    private String value;
    public Header(String caseSavedKey, String value) {
        this.caseSavedKey = caseSavedKey;
        this.value = value;
    }

    public String getCaseSavedKey() {
        return caseSavedKey;
    }

    public String getValue() {
        return value;
    }
}
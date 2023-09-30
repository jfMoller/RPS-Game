package com.example.rpsgame.dto;

import java.util.Map;

public interface JsonFormatProvider {
    String getJsonFormat(Map<String, Object> items);
    void printStyledJson(String json);

}

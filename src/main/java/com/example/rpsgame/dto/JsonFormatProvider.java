package com.example.rpsgame.dto;

import java.util.Map;

public interface JsonFormatProvider {
    String getJsonFormat(Map<String, Object> items);

    String formatJsonWithLinebreaks(String json);

}

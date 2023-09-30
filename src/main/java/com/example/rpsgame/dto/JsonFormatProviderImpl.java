package com.example.rpsgame.dto;

import java.util.Collection;
import java.util.Map;
import java.util.StringJoiner;

public class JsonFormatProviderImpl implements JsonFormatProvider {

    private static final String OPEN_CURLY_BRACE = "{";
    private static final String CLOSED_CURLY_BRACE = "}";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String COMMA = ",";
    private static final String DOUBLE_QUOTE = "\"";

    private static final String SEMI_COLON = ":";

    private static final String LINEBREAK = "\n";

    @Override
    public String getJsonFormat(Map<String, Object> items) {
        StringJoiner json = new StringJoiner(COMMA);

        for (Map.Entry<String, Object> entry : items.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String formattedValue =
                    (value instanceof Number || value instanceof Collection)
                            ? value.toString()
                            : DOUBLE_QUOTE + value + DOUBLE_QUOTE;
            json.add(DOUBLE_QUOTE + key + DOUBLE_QUOTE + SEMI_COLON + formattedValue);
        }

        return OPEN_CURLY_BRACE + json + CLOSED_CURLY_BRACE;
    }

    @Override
    public void printStyledJson(String json) {
        json = formatJsonWithLinebreaks(json);
        printJsonAsLightBlueText(json);
    }

    private void printJsonAsLightBlueText(String json) {
        System.out.println("\u001B[36m" + json + "\u001B[0m");
    }

    private String formatJsonWithLinebreaks(String json) {
        json = json.replaceAll("\\" + OPEN_CURLY_BRACE, OPEN_CURLY_BRACE + LINEBREAK);
        json = json.replaceAll(CLOSED_CURLY_BRACE, LINEBREAK + CLOSED_CURLY_BRACE);
        json = json.replaceAll("\\" + OPEN_SQUARE_BRACKET, LINEBREAK + OPEN_SQUARE_BRACKET);
        json = json.replaceAll(",", COMMA + LINEBREAK);

        return json;
    }
}

package dto;

import java.util.Map;

public class JsonFormatProvider {

    public static String getJsonFormat(Map<String, Object> items) {
        StringBuilder jsonBuilder = new StringBuilder("{");

        for (Map.Entry<String, Object> entry : items.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Append key-value pair to the JSON string
            jsonBuilder.append("\"").append(key).append("\":");

            // Check the type of value and format accordingly
            if (value instanceof String) {
                jsonBuilder.append("\"").append(value).append("\",");
            } else if (value instanceof Number) {
                jsonBuilder.append(value.toString().replace(',', '.')).append(",");
            } else {
                jsonBuilder.append(value).append(",");
            }
        }

        // Remove the trailing comma when there are no more items to format
        if (items.size() > 0) {
            jsonBuilder.setLength(jsonBuilder.length() - 1);
        }

        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}

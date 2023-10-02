package com.example.rpsgame.dto;

public class JsonPrintingProviderImpl implements JsonPrintingProvider {

    JsonFormatProvider jsonFormatProvider;

    public JsonPrintingProviderImpl() {
        this.jsonFormatProvider = new JsonFormatProviderImpl();
    }

    @Override
    public void printStyledJson(String json) {
        json = jsonFormatProvider.formatJsonWithLinebreaks(json);
        printJsonAsLightBlueText(json);
    }

    private void printJsonAsLightBlueText(String json) {
        System.out.println("\u001B[36m" + json + "\u001B[0m");
    }
}

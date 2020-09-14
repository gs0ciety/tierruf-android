package com.gs0ciety.tierruf.model;

public class LanguageItem {

    private final String countryName;
    private final String countryNameShort;
    private final int countryFlagResId;

    public LanguageItem(final String countryName,
                        final int countryFlagResId,
                        final String countryNameShort) {
        this.countryName = countryName;
        this.countryFlagResId = countryFlagResId;
        this.countryNameShort = countryNameShort;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryFlagResId() {
        return countryFlagResId;
    }

    public String getCountryNameShort() {
        return countryNameShort;
    }
}
package com.gs0ciety.model;

public class LanguageItem {

    private final String countryName;
    private final int countryFlagResId;

    public LanguageItem(final String countryName, final int countryFlagResId) {
        this.countryName = countryName;
        this.countryFlagResId = countryFlagResId;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryFlagResId() {
        return countryFlagResId;
    }

}
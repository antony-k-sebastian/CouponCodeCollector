package com.sample.couponcodecollector.Enum;

public enum UsageType {
    SINGLE_USE("Single Use"),
    MULTIPLE_USE("Multiple Use"), 
    UNLIMITED("Unlimited");

    private final String displayName;

    UsageType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
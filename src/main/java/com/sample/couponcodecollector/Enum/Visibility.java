package com.sample.couponcodecollector.Enum;

public enum Visibility {
    PUBLIC("Public"),
    PRIVATE("Private");

    private final String displayName;

    Visibility(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
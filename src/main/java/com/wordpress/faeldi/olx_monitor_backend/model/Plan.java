package com.wordpress.faeldi.olx_monitor_backend.model;

public enum Plan {
    USER(0),
    ONE(5),
    TWO(10),
    THREE(15);

    private final int keywordLimit;

    Plan(int keywordLimit) {
        this.keywordLimit = keywordLimit;
    }

    public int getKeywordLimit() {
        return keywordLimit;
    }
}

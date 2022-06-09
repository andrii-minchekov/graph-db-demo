package com.example.pupularity.domain;

public enum SentimentValue {
    NEGATIVE(0), POSITIVE(2), NEUTRAL(1);

    private final int value;

    SentimentValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

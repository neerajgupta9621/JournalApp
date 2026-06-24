package com.edigest.my.first.project.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Sentiment {

    HAPPY,
    SAD,
    ANGRY,

    IT,
    HR,
    FINANCE,
    SALES,
    MARKETING;

    @JsonCreator
    public static Sentiment from(String value) {
        return value == null ? null : Sentiment.valueOf(value.toUpperCase());
    }
}
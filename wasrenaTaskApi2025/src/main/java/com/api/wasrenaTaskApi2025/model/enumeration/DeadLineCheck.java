package com.api.wasrenaTaskApi2025.model.enumeration;

public enum DeadLineCheck {
    DailyOnce("DailyOnce"),
    DailyHour("DailyHour"),
    WeeklyDay("WeeklyDay"),
    WeeklyDayInterval("WeeklyDayInterval"),
    MonthOnce("MonthOnce"),
    MonthDate("MonthDate"),
    YearOnceDate("YearOnceDate");

    private final String deadLineCheck;

    public String getValue() {  // GraphQLライブラリが使用する可能性
        return deadLineCheck;
    }

    private DeadLineCheck(String deadLineCheck) {
        this.deadLineCheck = deadLineCheck;
    }
}

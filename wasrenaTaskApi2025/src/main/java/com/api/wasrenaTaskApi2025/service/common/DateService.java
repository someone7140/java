package com.api.wasrenaTaskApi2025.service.common;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class DateService {

    // 現在時刻をオフセット指定で取得
    public OffsetDateTime getNowDateTime() {
        return OffsetDateTime.now(ZoneOffset.of("+09:00"));
    }

    // 登録されている週の曜日の値をenumに変換
    public DayOfWeek convertRegisteredWeeklyDayToEnum(int weeklyDay) {
        // 日曜日の場合は「7」にする
        return DayOfWeek.of(weeklyDay == 0 ? 7 : weeklyDay);
    }

}

package com.api.wasrenaTaskApi2025.model.domain;

import com.api.wasrenaTaskApi2025.model.enumeration.DeadLineCheck;
import com.api.wasrenaTaskApi2025.service.common.DateService;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCheckLogic {

    @Autowired
    DateService dateService;

    private String id;
    private String title;
    private String ownerUserId;
    private DeadLineCheck deadLineCheck;
    private Optional<Map<String, Object>> deadLineCheckSubSetting;
    private Optional<OffsetDateTime> latestExecDateTime;

    // タスクの期限超過チェック
    public Boolean getIsExceededDeadLine(OffsetDateTime now) {
        // 実行履歴がない場合は期限超過とみなす
        if (latestExecDateTime.isEmpty()) {
            return true;
        }

        switch (deadLineCheck) {
            case DeadLineCheck.DailyOnce:
                return isExceedDailyOnce(now);
            case DeadLineCheck.DailyHour:
                return isExceedDailyHour(now);
            case DeadLineCheck.WeeklyDay:
                return isExceedWeeklyDay(now);
            case DeadLineCheck.WeeklyDayInterval:
                return isExceedWeeklyDayInterval(now);
            case DeadLineCheck.MonthOnce:
                return isExceedMonthOnce(now);
            case DeadLineCheck.MonthDate:
                return isExceedMonthDate(now);
            case DeadLineCheck.YearOnceDate:
                return isExceedYearDate(now);
            default:
                return false;
        }
    }

    // 次回の実行期限を取得
    public Optional<OffsetDateTime> getNextDeadLineDateTime(OffsetDateTime now) {
        switch (deadLineCheck) {
            case DeadLineCheck.DailyOnce:
                return getNextDeadLineDateTimeDailyOnce(now);
            case DeadLineCheck.DailyHour:
                return getNextDeadLineDateTimeDailyHour(now);
            case DeadLineCheck.WeeklyDay:
                return getNextDeadLineDateWeeklyDay(now);
            case DeadLineCheck.WeeklyDayInterval:
                return getNextDeadLineDateWeeklyDayInterval(now);
            case DeadLineCheck.MonthOnce:
                return getNextDeadLineDateMonthOnce(now);
            case DeadLineCheck.MonthDate:
                return getNextDeadLineDateMonthDate(now);
            case DeadLineCheck.YearOnceDate:
                return getNextDeadLineDateYearDate(now);
            default:
                return Optional.empty();
        }
    }

    // 期限のサブ設定から指定した型のValueを取得
    private <T> Optional<T> getDeadLineCheckSubSettingValue(String key, Class<T> targetType) {
        return deadLineCheckSubSetting
                .map(s -> s.get(key)) // Optional<Object>
                .filter(Objects::nonNull) // nullを除外
                .filter(targetType::isInstance) // 型チェック
                .map(targetType::cast); // 型安全なキャスト
    }

    // 日単位のタスクの期限が超過してるか
    private Boolean isExceedDailyOnce(OffsetDateTime now) {
        return ChronoUnit.HOURS.between(latestExecDateTime.get(), now) > 24;
    }

    // 時間単位のタスクの期限が超過してるか
    private Boolean isExceedDailyHour(OffsetDateTime now) {
        var hourValueOpt = getDeadLineCheckSubSettingValue("hourInterval", Integer.class);
        if (hourValueOpt.isEmpty()) {
            return false;
        }

        // 設定した時間より現在日時と実行日時の差分が大きいか
        return ChronoUnit.HOURS.between(latestExecDateTime.get(), now) > hourValueOpt.get();
    }

    // 曜日指定のタスクの期限が超過してるか
    private Boolean isExceedWeeklyDay(OffsetDateTime now) {
        var weeklyDayValueOpt = getDeadLineCheckSubSettingValue("weeklyDay", Integer.class);
        if (weeklyDayValueOpt.isEmpty()) {
            return false;
        }

        // 設定されている曜日
        var settingDayOfWeek = dateService.convertRegisteredWeeklyDayToEnum(weeklyDayValueOpt.get());
        // 現在日時の曜日
        var nowDayOfWeek = now.getDayOfWeek();

        // 曜日の比較で期限を決定
        var targetDateTime = OffsetDateTime.now();
        if (nowDayOfWeek.getValue() < settingDayOfWeek.getValue()) {
            // 設定曜日の方が後の場合は先週の設定曜日の日付
            targetDateTime = now.minusDays(7 - (settingDayOfWeek.getValue() - nowDayOfWeek.getValue()));
        } else {
            // 今週の設定曜日の日付
            targetDateTime = now.minusDays(nowDayOfWeek.getValue() - settingDayOfWeek.getValue());
        }

        // 直近の実行日時と期限の差分が1週間より大きいか
        return ChronoUnit.HOURS.between(latestExecDateTime.get(), now) > 7 * 24;
    }

    // 指定された週間隔のタスクの期限が超過しているか
    private Boolean isExceedWeeklyDayInterval(OffsetDateTime now) {
        var weeklyDayValueOpt = getDeadLineCheckSubSettingValue("weekInterval", Integer.class);
        if (weeklyDayValueOpt.isEmpty()) {
            return false;
        }

        // 直近の実行日時と期限の差分が指定された週間隔より大きいか
        return ChronoUnit.HOURS.between(latestExecDateTime.get(), now) > 7 * 24 * weeklyDayValueOpt.get();
    }

    // 月に一度のタスクの期限が超過しているか
    private Boolean isExceedMonthOnce(OffsetDateTime now) {
        // 今月の1日を取得してから1ヶ月戻る
        var firstDateOfThisMonth = now.withDayOfMonth(1).withHour(0);
        var firstDateOfLastMonth = firstDateOfThisMonth.minusMonths(1);

        return ChronoUnit.HOURS.between(firstDateOfLastMonth, latestExecDateTime.get()) < 0;
    }

    // 指定した月の日のタスクの期限が超過しているか
    private Boolean isExceedMonthDate(OffsetDateTime now) {
        var monthlyDayValueOpt = getDeadLineCheckSubSettingValue("monthlyDay", Integer.class);
        if (monthlyDayValueOpt.isEmpty()) {
            return false;
        }

        // 現在日時の日より対象日が小さい場合
        if (now.getDayOfMonth() > monthlyDayValueOpt.get()) {
            // 今月の1日を取得
            var firstDateOfThisMonth = now.withDayOfMonth(1).withHour(0);
            return ChronoUnit.HOURS.between(firstDateOfThisMonth, latestExecDateTime.get()) < 0;
        }

        // 先月の該当日との比較
        var lastMonthDate = now.minusMonths(1).withHour(0);
        var lastMonthTargetDate = lastMonthDate.withDayOfMonth(monthlyDayValueOpt.get());

        return ChronoUnit.HOURS.between(lastMonthTargetDate, latestExecDateTime.get()) < 0;
    }

    // 年の指定した日のタスクの期限が超過しているか
    private Boolean isExceedYearDate(OffsetDateTime now) {
        var yearDateValueOpt = getDeadLineCheckSubSettingValue("yearDate", String.class);
        if (yearDateValueOpt.isEmpty()) {
            return false;
        }

        // 月と日に分解
        var settingMonth = Integer.parseInt(yearDateValueOpt.get().split("-")[0]);
        var settingDay = Integer.parseInt(yearDateValueOpt.get().split("-")[1]);

        var beforeYearDate = now.minusYears(1).withMonth(settingMonth).withDayOfMonth(settingDay).withHour(0);
        var thisYearDate = now.withMonth(settingMonth).withDayOfMonth(settingDay).withHour(0);

        // 現在の方が月が小さい場合
        if (now.getMonth().getValue() < settingMonth) {
            // 昨年の該当日との比較
            return ChronoUnit.HOURS.between(beforeYearDate, latestExecDateTime.get()) < 0;
        } else if (now.getMonth().getValue() > settingMonth) { // 現在の方が月が大きい場合
            // 今年の該当日と比較
            return ChronoUnit.HOURS.between(thisYearDate, latestExecDateTime.get()) < 0;
        } else { // 月が同じ場合
            // 現在の日以下の場合
            if (now.getDayOfMonth() <= settingDay) {
                // 昨年の該当日との比較
                return ChronoUnit.HOURS.between(beforeYearDate, latestExecDateTime.get()) < 0;
            } else { // 現在の日付が大きい場合
                // 今年の該当日と比較
                return ChronoUnit.HOURS.between(thisYearDate, latestExecDateTime.get()) < 0;
            }
        }
    }

    // 日単位のタスクの次回実行日時
    private Optional<OffsetDateTime> getNextDeadLineDateTimeDailyOnce(OffsetDateTime now) {
        // 超過している場合
        if (latestExecDateTime.isEmpty() || isExceedDailyOnce(now)) {
            // 現在日付を返す
            return Optional.of(now);
        } else {
            // 直近の実行日時から24時間を足す
            return Optional.of(latestExecDateTime.get().plusDays(1));
        }
    }

    // 時間単位のタスクの次回実行日時
    private Optional<OffsetDateTime> getNextDeadLineDateTimeDailyHour(OffsetDateTime now) {
        var hourValueOpt = getDeadLineCheckSubSettingValue("hourInterval", Integer.class);
        if (hourValueOpt.isEmpty()) {
            return Optional.empty();
        }

        // すでに超過している場合
        if (latestExecDateTime.isEmpty() || isExceedDailyHour(now)) {
            // 現在日付を返す
            return Optional.of(now);
        } else {
            // 直近の実行日時から設定時間を足す
            var nextDeadLineDateTime = latestExecDateTime.get().plusHours(hourValueOpt.get());
            return Optional.of(nextDeadLineDateTime);
        }
    }

    // 週の曜日単位のタスクの次回実行日時
    private Optional<OffsetDateTime> getNextDeadLineDateWeeklyDay(OffsetDateTime now) {
        var weeklyDayValueOpt = getDeadLineCheckSubSettingValue("weeklyDay", Integer.class);
        if (weeklyDayValueOpt.isEmpty()) {
            return Optional.empty();
        }
        // 設定されている曜日
        var settingDayOfWeek = dateService.convertRegisteredWeeklyDayToEnum(weeklyDayValueOpt.get());

        if (now.getDayOfWeek().getValue() == settingDayOfWeek.getValue()) {
            return Optional.of(now.plusDays(7));
        } else if (now.getDayOfWeek().getValue() < settingDayOfWeek.getValue()) {
            return Optional.of(now.plusDays(settingDayOfWeek.getValue() - now.getDayOfWeek().getValue()));
        } else {
            return Optional.of(now.plusDays(now.getDayOfWeek().getValue() - settingDayOfWeek.getValue()));
        }
    }

    // 週間隔でのタスクの次回実行日時
    private Optional<OffsetDateTime> getNextDeadLineDateWeeklyDayInterval(OffsetDateTime now) {
        var weeklyDayValueOpt = getDeadLineCheckSubSettingValue("weekInterval", Integer.class);
        if (weeklyDayValueOpt.isEmpty()) {
            return Optional.empty();
        }

        // すでに超過している場合
        if (latestExecDateTime.isEmpty() || isExceedWeeklyDayInterval(now)) {
            // 現在日付を返す
            return Optional.of(now);
        } else {
            // 直近の実行日時から週間隔の日付を足す
            var nextDeadLineDateTime = latestExecDateTime.get().plusDays(weeklyDayValueOpt.get() * 7);
            return Optional.of(nextDeadLineDateTime);
        }
    }

    // 月に一度のタスクの次回実行日時
    private Optional<OffsetDateTime> getNextDeadLineDateMonthOnce(OffsetDateTime now) {
        // 今月の1日
        var firstDayThisMonth = now.withDayOfMonth(1);

        // 今月の1日以降に実行されているか
        if (latestExecDateTime.isPresent()
                && ChronoUnit.HOURS.between(firstDayThisMonth.withHour(0), latestExecDateTime.get()) >= 0) {
            // 来月の末日
            var nextDeadLineDateTime = firstDayThisMonth.plusMonths(2).minusDays(-1);
            return Optional.of(nextDeadLineDateTime);
        } else {
            // 今月の末日
            var nextDeadLineDateTime = firstDayThisMonth.plusMonths(1).minusDays(-1);
            return Optional.of(nextDeadLineDateTime);
        }
    }

    // 指定した月の日のタスクの次回実行日時
    private Optional<OffsetDateTime> getNextDeadLineDateMonthDate(OffsetDateTime now) {
        var monthlyDayValueOpt = getDeadLineCheckSubSettingValue("monthlyDay", Integer.class);
        if (monthlyDayValueOpt.isEmpty()) {
            return Optional.empty();
        }
        var targetOfThisMonth = now.withDayOfMonth(monthlyDayValueOpt.get());

        // 現在日時の日より対象日が小さい場合
        if (ChronoUnit.HOURS.between(now, targetOfThisMonth) < 0) {
            // 来月の指定日
            var targetOfNextMonth = targetOfThisMonth.plusMonths(1);
            return Optional.of(targetOfNextMonth);
        }

        return Optional.of(targetOfThisMonth);
    }

    // 年の指定した日のタスクの次回実行日時
    private Optional<OffsetDateTime> getNextDeadLineDateYearDate(OffsetDateTime now) {
        var yearDateValueOpt = getDeadLineCheckSubSettingValue("yearDate", String.class);
        if (yearDateValueOpt.isEmpty()) {
            return Optional.empty();
        }

        // 月と日に分解
        var settingMonth = Integer.parseInt(yearDateValueOpt.get().split("-")[0]);
        var settingDay = Integer.parseInt(yearDateValueOpt.get().split("-")[1]);

        var thisYearDate = now.withMonth(settingMonth).withDayOfMonth(settingDay);
        var nextYearDate = thisYearDate.plusYears(1);

        // 現在の方が月が小さい場合
        if (now.getMonth().getValue() < settingMonth) {
            return Optional.of(thisYearDate);
        } else if (now.getMonth().getValue() > settingMonth) { // 現在の方が月が大きい場合
            return Optional.of(nextYearDate);
        } else { // 月が同じ場合
            // 現在の日以下の場合
            if (now.getDayOfMonth() <= settingDay) {
                return Optional.of(thisYearDate);
            } else { // 現在の日付が大きい場合
                return Optional.of(nextYearDate);
            }
        }
    }

}

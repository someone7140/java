package com.api.wasrenaTaskApi2025.model.db.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Converter
public class OffsetDateTimeConverter
        implements AttributeConverter<OffsetDateTime, OffsetDateTime> {

    @Override
    public OffsetDateTime convertToDatabaseColumn(OffsetDateTime attribute) {
        // 保存時はそのまま
        return attribute;
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(OffsetDateTime dbData) {
        if (dbData == null) return null;

        // 取得時の処理 - 特定のオフセットを適用
        ZoneOffset targetOffset = ZoneOffset.of("+09:00"); // 東京時間
        return dbData.withOffsetSameInstant(targetOffset);
    }
}
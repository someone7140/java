package com.api.wasrenaTaskApi2025.model.db;

import com.api.wasrenaTaskApi2025.model.db.converter.OffsetDateTimeConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_accounts")
public class UserAccountEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_setting_id")
    private String userSettingId;

    @Column(name = "line_id")
    private String lineId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_line_bot_follow")
    private Boolean isLineBotFollow;

    @Column(name = "line_notify_latest_date")
    @Convert(converter = OffsetDateTimeConverter.class)
    private OffsetDateTime lineNotifyLatestDate;
}

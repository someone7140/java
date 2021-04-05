package com.ddd.sampleDomain.item;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ItemFood extends Item {
    private LocalDateTime expirationDate;
}

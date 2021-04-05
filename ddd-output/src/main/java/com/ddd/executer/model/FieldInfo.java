package com.ddd.executer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class FieldInfo {
    private String fieldName;
    private String type;
    private boolean collectionOrArrayFlag;
}

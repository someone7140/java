package com.ddd.executer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class MethodInfo {
    private String methodName;
    private String visibility;
    private List<String> params;
    private String returnType;
    private String comment;
    private Boolean overrideFlag;
}

package com.ddd.executer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClassInfo {
    private String fullClassName;
    private String packageName;
    private String className;
    private String classNameGiven;
    private String type;
    private String fullSuperClassName;
    private String comment;
    private List<String> fullInterfaceNameList;
    private List<MethodInfo> methodInfoList;
    private List<FieldInfo> fieldInfoList;
    private Map<String, List<String>> methodCallMap;

}

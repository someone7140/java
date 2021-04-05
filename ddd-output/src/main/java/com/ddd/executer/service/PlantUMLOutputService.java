package com.ddd.executer.service;

import com.ddd.executer.model.ClassInfo;
import com.ddd.executer.model.FieldInfo;
import com.ddd.executer.model.MethodInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PlantUMLOutputService {
    public static void outputPuFile(
            String outputFilePath,
            List<ClassInfo> classInfoList,
            Map<String, Map<String, List<String>>> classMethodCallMap
    ) {
        // 出力内容
        StringBuilder result = new StringBuilder();
        result.append("@startuml ddd-output");
        result.append(System.getProperty("line.separator"));
        // output済みのクラスを入れるリスト
        List<ClassInfo> outputClassList = new ArrayList<>();
        // output済みのフィールドを入れるMap
        Map<String, List<FieldInfo>> outputFieldMap = new HashMap<>();
        // パッケージ毎にクラスをグルーピング
        Map<String, List<ClassInfo>> grpClassByPackage = classInfoList.stream().collect(
                Collectors.groupingBy(ClassInfo::getPackageName)
        );
        grpClassByPackage.forEach((packageName, classInfoListByPackage) -> {
            result.append("package ");
            result.append(packageName);
            result.append(" {");
            result.append(System.getProperty("line.separator"));
            classInfoListByPackage.stream().forEach(classInfo -> {
                result.append(classInfo.getType() + " " + classInfo.getFullClassName());
                if (!StringUtils.isEmpty(classInfo.getClassNameGiven())) {
                    result.append(" <<" + classInfo.getClassNameGiven() + ">>");
                }
                result.append(" {");
                result.append(System.getProperty("line.separator"));
                // フィールド情報
                List<FieldInfo> fields = classInfo.getFieldInfoList();
                if (fields != null) {
                    fields.stream().forEach(field -> {
                        result.append(System.getProperty("line.separator"));
                        result.append("{field} " + field.getType() + " " + field.getFieldName());
                    });
                }
                // メソッド情報
                List<MethodInfo> methods = classInfo.getMethodInfoList();
                if (methods != null) {
                    methods.stream().forEach(method -> {
                        result.append(System.getProperty("line.separator"));
                        result.append("{method} " +
                                method.getVisibility() + method.getReturnType() + " ");
                        result.append(getMethodNameAndParameter(method));
                    });
                }
                result.append(System.getProperty("line.separator"));
                result.append("}");
                result.append(System.getProperty("line.separator"));
                // クラスへのコメント
                if (!StringUtils.isEmpty(classInfo.getComment())) {
                    result.append("note left: " + classInfo.getComment());
                    result.append(System.getProperty("line.separator"));
                }
                // メソッドへのコメント
                methods.stream().filter(m -> !StringUtils.isEmpty(m.getComment())).forEach(m -> {
                            result.append("note right of " + classInfo.getFullClassName() + "::\"" +
                                    getMethodNameAndParameter(m) + "\" ");
                            result.append(System.getProperty("line.separator"));
                            result.append(" " + m.getComment());
                            result.append(System.getProperty("line.separator"));
                            result.append("end note");
                            result.append(System.getProperty("line.separator"));
                        }
                );
                // 親クラスへの紐付け（すでに親クラスが出力済み）
                if (StringUtils.isNotEmpty(classInfo.getFullSuperClassName())) {
                    Optional<ClassInfo> superClass = outputClassList.stream().filter(o ->
                            o.getFullClassName().equals(classInfo.getFullSuperClassName())
                    ).findFirst();
                    if (superClass.isPresent()) {
                        outputExtendsOrImplements(result, superClass.get().getFullClassName(), classInfo.getFullClassName());
                    }
                }
                // 親インターフェースへの紐付け（すでに親インターフェースが出力済み）
                if (CollectionUtils.isNotEmpty(classInfo.getFullInterfaceNameList())) {
                    classInfo.getFullInterfaceNameList().stream().forEach(i -> {
                        Optional<ClassInfo> interfaceClassOpt = outputClassList.stream().filter(o ->
                                o.getFullClassName().equals(i)
                        ).findFirst();
                        if (interfaceClassOpt.isPresent()) {
                            outputExtendsOrImplements(result, interfaceClassOpt.get().getFullClassName(), classInfo.getFullClassName());
                        }
                    });
                }
                // 自分が親クラスもしくはインターフェース
                outputClassList.stream().forEach(outputClass -> {
                    if ("class".equals(classInfo.getType())) {
                        // 親クラスへの紐付け（自分が親クラス）
                        if (classInfo.equals(outputClass.getFullSuperClassName())) {
                            outputExtendsOrImplements(result, classInfo.getFullClassName(), outputClass.getFullClassName());
                        }
                    }
                    if ("interface".equals(classInfo.getType())) {
                        // 親インターフェースへの紐付け（自分が親インターフェース）
                        if (CollectionUtils.isNotEmpty(classInfo.getFullInterfaceNameList()) &&
                                classInfo.getFullInterfaceNameList().contains(classInfo.getFullClassName())
                        ) {
                            outputExtendsOrImplements(result, classInfo.getFullClassName(), outputClass.getFullClassName());
                        }
                    }
                });
                // フィールド情報とクラスとの紐付け（すでに対象クラスが出力済み）
                if (fields != null) {
                    // クラス情報に登録されているフィールド
                    fields.stream().forEach(f -> {
                        Optional<ClassInfo> registeredClassOpt = classInfoList.stream().filter(c ->
                                f.getType().contains(c.getFullClassName())
                        ).findFirst();
                        if (registeredClassOpt.isPresent()) {
                            // すでに出力済みのクラスにあるか
                            Optional<ClassInfo> outputedClassOpt = outputClassList.stream().filter(c ->
                                    f.getType().contains(c.getFullClassName())
                            ).findFirst();
                            if (outputedClassOpt.isPresent()) {
                                outputFieldLink(result, classInfo.getFullClassName(), f, outputedClassOpt.get());
                            }
                            // 登録済みのフィールドとして登録
                            List<FieldInfo> registeredFieldList = outputFieldMap.get(classInfo.getFullClassName());
                            if (CollectionUtils.isNotEmpty(registeredFieldList)) {
                                registeredFieldList.add(f);
                                outputFieldMap.put(classInfo.getFullClassName(), registeredFieldList);
                            } else {
                                outputFieldMap.put(classInfo.getFullClassName(), Arrays.asList(f));
                            }
                        }
                    });
                }
                // フィールド情報とクラスとの紐付け（自クラスがフィールド情報として登録）
                outputFieldMap.forEach((className, fieldLists) -> {
                    Optional<FieldInfo> fieldInfoOpt = fieldLists.stream().filter(f ->
                            f.getType().contains(classInfo.getFullClassName())
                    ).findFirst();
                    if (fieldInfoOpt.isPresent()) {
                        outputFieldLink(result, className, fieldInfoOpt.get(), classInfo);
                    }
                });
                // 自クラスが参照しているメソッドの関連
                Map<String, List<String>> ownClassMethodCallMap = classMethodCallMap.get(classInfo.getFullClassName());
                if (MapUtils.isNotEmpty(ownClassMethodCallMap)) {
                    // すでに出力されたクラスのみが対象
                    ownClassMethodCallMap.forEach((calledClassName, methodCallList) -> {
                        if (outputClassList.stream().anyMatch(o -> o.getFullClassName().equals(calledClassName))) {
                            outputMethodCall(
                                    result,
                                    classInfo,
                                    classInfoList.stream().filter(c -> c.getFullClassName().equals(calledClassName)).findFirst(),
                                    methodCallList
                            );
                        }
                    });
                }
                // すでに出力されているクラスから自クラスが参照されているメソッドの関連
                outputClassList.stream().forEach(callClass -> {
                    Map<String, List<String>> otherClassMethodCallMap = classMethodCallMap.get(callClass.getFullClassName());
                    if (MapUtils.isNotEmpty(otherClassMethodCallMap)) {
                        otherClassMethodCallMap.forEach((calledClassName, methodCallList) -> {
                            if (calledClassName.equals(classInfo.getFullClassName())) {
                                outputMethodCall(
                                        result,
                                        callClass,
                                        classInfoList.stream().filter(c -> c.getFullClassName().equals(calledClassName)).findFirst(),
                                        methodCallList
                                );
                            }
                        });
                    }
                });
                // 出力済みクラスの登録
                outputClassList.add(classInfo);
            });
            result.append(System.getProperty("line.separator"));
            result.append("}");
            result.append(System.getProperty("line.separator"));
        });


        result.append(System.getProperty("line.separator"));
        result.append("@enduml");
        result.append(System.getProperty("line.separator"));
        try {
            FileWriter fw = new FileWriter(outputFilePath, false);
            fw.write(result.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 出力用の関数名とパラメータの取得
    private static String getMethodNameAndParameter(MethodInfo m) {
        String params = m.getParams() != null ? String.join(", ", m.getParams()) : "";
        return m.getMethodName() + "(" + params + ")";
    }

    // 継承の出力
    private static void outputExtendsOrImplements(
            StringBuilder result, String parentClassName, String childClassName
    ) {
        result.append(parentClassName + " <|--- " + childClassName);
        result.append(System.getProperty("line.separator"));
    }

    // フィールドとクラスの紐付け出力
    private static void outputFieldLink(
            StringBuilder result, String settingClassName, FieldInfo fieldInfo, ClassInfo originalClassInfo
    ) {
        // 完全一致
        if (fieldInfo.getType().equals(originalClassInfo.getFullClassName())) {
            result.append(settingClassName + " -- " + originalClassInfo.getFullClassName());
            result.append(System.getProperty("line.separator"));
            // 部分一致
        } else if (fieldInfo.getType().contains(originalClassInfo.getFullClassName())) {
            result.append(settingClassName + " *-- " + originalClassInfo.getFullClassName());
            result.append(System.getProperty("line.separator"));
        }
    }

    // メソッドの呼び出しを出力
    private static void outputMethodCall(
            StringBuilder result, ClassInfo callClass, Optional<ClassInfo> calledClassOpt, List<String> methodCallList
    ) {
        if (calledClassOpt.isPresent()) {
            ClassInfo calledClass = calledClassOpt.get();
            // 親クラスではない
            if (!(StringUtils.isNotEmpty(calledClass.getFullSuperClassName()) && calledClass.getFullSuperClassName().equals(callClass.getFullSuperClassName())) &&
                    !(CollectionUtils.isNotEmpty(callClass.getFullInterfaceNameList()) && callClass.getFullInterfaceNameList().contains(calledClass.getFullClassName()))
            ) {
                // DDD出力メソッドの抽出
                List<String> outputMethods = methodCallList.stream().filter(m -> calledClass.getMethodInfoList().stream().anyMatch(cm -> cm.getMethodName().equals(m))).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(outputMethods)) {
                    result.append(callClass.getFullClassName() + " ..> " + calledClass.getFullClassName() + " : " + String.join(",", outputMethods) + " >");
                    result.append(System.getProperty("line.separator"));
                }
            }
        }
    }
}

package com.ddd.executer.service;

import com.ddd.annotation.DddOutputClass;
import com.ddd.annotation.DddOutputMethod;
import com.ddd.executer.model.ClassInfo;
import com.ddd.executer.model.FieldInfo;
import com.ddd.executer.model.MethodInfo;
import com.google.common.collect.Streams;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.stream.Collectors;

public class ClassAnalyticsService {
    public static List<ClassInfo> getClassInfoList(Map<String, Class> classMap) {
        List<ClassInfo> classInfoList = new ArrayList<>();
        // アノテーションがついているクラスでまずは基本情報を取得
        classMap.forEach((classFullName, c) -> {
            addListClass(classInfoList, c, classFullName);
        });
        return classInfoList;
    }

    private static void addListClass(List<ClassInfo> classInfoList, Class c, String classFullName) {
        DddOutputClass classAnnotation = (DddOutputClass) c.getAnnotation(DddOutputClass.class);
        if (classAnnotation != null) {
            List<MethodInfo> methodInfoList = getMethodInfoList(c);
            List<FieldInfo> fieldInfoList = getFieldInfoList(c);
            Class superClass = c.getSuperclass();
            String superClassName = null;
            if (superClass != null && !"java.lang.Object".equals(superClass.getName())) {
                superClassName = superClass.getName();
                // superClassの情報の登録
                if (
                        !classInfoList.stream().anyMatch(ci -> ci.getFullClassName().equals(superClass.getName()))
                ) {
                    addListClass(classInfoList, superClass, superClassName);
                }
            }
            Class[] interfaces = c.getInterfaces();
            List<String> interfaceNames = new ArrayList<>();
            if (interfaces != null) {
                Arrays.stream(interfaces).forEach(i -> {
                            if (
                                    !classInfoList.stream().anyMatch(ci -> ci.getFullClassName().equals(i.getName()))
                            ) {
                                addListInterface(
                                        classInfoList,
                                        i,
                                        i.getName(),
                                        methodInfoList.stream().filter(m -> m.getVisibility().equals("+")).collect(Collectors.toList())
                                );
                            }
                            interfaceNames.add(i.getName());
                        }
                );
            }
            classInfoList.add(
                    ClassInfo.builder()
                            .fullClassName(classFullName)
                            .packageName(c.getPackageName())
                            .className(c.getSimpleName())
                            .classNameGiven(classAnnotation.name())
                            .type(c.isInterface() ? "interface" : "class")
                            .comment(classAnnotation.comment())
                            .methodInfoList(methodInfoList)
                            .fieldInfoList(fieldInfoList)
                            .fullSuperClassName(superClassName)
                            .fullInterfaceNameList(interfaceNames)
                            .build()
            );
        }
    }

    private static void addListInterface(
            List<ClassInfo> classInfoList,
            Class interfaceClass,
            String classFullName,
            List<MethodInfo> methodInfoList
    ) {
        DddOutputClass classAnnotation = (DddOutputClass) interfaceClass.getAnnotation(DddOutputClass.class);
        if (classAnnotation != null) {
            classInfoList.add(
                    ClassInfo.builder()
                            .fullClassName(classFullName)
                            .packageName(interfaceClass.getPackageName())
                            .className(interfaceClass.getSimpleName())
                            .classNameGiven(classAnnotation.name())
                            .type("interface")
                            .comment(classAnnotation.comment())
                            .methodInfoList(methodInfoList.stream().filter(m -> m.getOverrideFlag()).collect(Collectors.toList()))
                            .build()
            );
        }
    }

    private static List<MethodInfo> getMethodInfoList(Class c) {
        return Arrays.stream(c.getDeclaredMethods())
                .map(m -> {
                    DddOutputMethod methodAnnotation = (DddOutputMethod) m.getAnnotation(DddOutputMethod.class);
                    if (methodAnnotation != null) {
                        return getMethodInfo(m, methodAnnotation);
                    }
                    return null;
                })
                .filter(m -> m != null)
                .collect(Collectors.toList());
    }

    private static MethodInfo getMethodInfo(Method m, DddOutputMethod methodAnnotation) {
        Type[] genericParameterTypes = m.getGenericParameterTypes();
        return MethodInfo.builder()
                .methodName(m.getName())
                .comment(methodAnnotation.comment())
                .visibility(getVisibility(m.getModifiers()))
                .returnType(getClassNameWithGenerics(m.getReturnType(), m.getGenericReturnType()))
                .params(
                        Streams.mapWithIndex(
                                Arrays.stream(m.getParameters()),
                                (p, i) -> {
                                    String paramName = p.getName();
                                    String paramType = getClassNameWithGenerics(p.getType(), genericParameterTypes[(int) i]);
                                    return paramType + " " + paramName;
                                })
                                .collect(Collectors.toList())
                )
                .overrideFlag(methodAnnotation.override())
                .build();
    }

    private static String getVisibility(int mod) {
        if (Modifier.isPublic(mod)) return "+";
        if (Modifier.isPrivate(mod)) return "-";
        if (Modifier.isProtected(mod)) return "#";
        return "~";
    }

    private static List<FieldInfo> getFieldInfoList(Class c) {
        return Arrays.stream(c.getDeclaredFields())
                .map(f -> {
                    Class fieldType = f.getType();
                    return FieldInfo.builder()
                            .fieldName(f.getName())
                            .type(getClassNameWithGenerics(fieldType, f.getGenericType()))
                            .collectionOrArrayFlag(isCollectionOrArrayFlag(fieldType))
                            .build();
                })
                .collect(Collectors.toList());
    }

    private static boolean isCollectionOrArrayFlag(Class c) {
        return c.isArray() || Collection.class.isAssignableFrom(c);
    }

    private static String getClassNameWithGenerics(Class c, Type genericsType) {
        if (c.isArray()) {
            return c.getName()
                    .replace("[L", "")
                    .replace(";", "") + "[]";
        }
        return genericsType.getTypeName();
    }
}

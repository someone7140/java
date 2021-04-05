package com.ddd.executer;

import com.ddd.executer.model.ClassInfo;
import com.ddd.executer.service.ClassAnalyticsService;
import com.ddd.executer.service.ClassLoaderService;
import com.ddd.executer.service.DirectoryService;
import com.ddd.executer.service.PlantUMLOutputService;

import java.util.*;
import java.util.stream.Collectors;

public class Executer {

    public static void outputPuFile(String classFilePath, String outputFilePath, String packageName) {
        ClassLoaderService classLoaderService = new ClassLoaderService();
        try {
            // ディレクトリとパッケージを指定しクラスファイルのパスを取得
            List<String> classFilePaths = DirectoryService.getClassFilePathList(classFilePath, packageName);
            Collections.sort(classFilePaths);
            Map<String, String> fullNameClassPathMap = new LinkedHashMap<>();
            classFilePaths.stream().forEach(path -> {
                String fullClassName = classLoaderService.getFullClassNameFromPath(path, packageName);
                fullNameClassPathMap.put(fullClassName, path);
            });
            // クラスファイルからクラスの情報を取得
            Map<String, Class> classMap = new LinkedHashMap<>();
            fullNameClassPathMap.forEach((fullClassName, path) -> {
                try {
                    Class classFormPath = classLoaderService.getClassFromFilePath(path);
                    if (classFormPath != null) {
                        classMap.put(fullClassName, classFormPath);
                    }
                } catch (Exception e) {
                    // exception発生時は何もしない
                }
            });
            // クラス情報を解析して結果を返す
            List<ClassInfo> classInfoList = ClassAnalyticsService.getClassInfoList(classMap);
            // クラスファイルからコンスタントプール情報を取得
            Map<String, Map<String, List<String>>> classMethodCallMap = new HashMap<>();
            fullNameClassPathMap.forEach((fullClassName, path) -> {
                // classInfoListに存在するクラスのみ
                Optional<ClassInfo> registeredClass = classInfoList.stream().filter(c -> c.getFullClassName().equals(fullClassName)).findFirst();
                if (registeredClass.isPresent()) {
                    try {
                        Map<String, List<String>> calledMethodMap = classLoaderService.getClassMethodCallMap(path, fullNameClassPathMap, fullClassName);
                        classMethodCallMap.put(fullClassName, calledMethodMap);
                    } catch (Exception e) {
                        // 何もしない
                    }
                }
            });
            // PlantUMLファイルの出力
            PlantUMLOutputService.outputPuFile(outputFilePath, classInfoList, classMethodCallMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

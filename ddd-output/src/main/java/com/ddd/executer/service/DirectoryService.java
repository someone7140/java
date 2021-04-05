package com.ddd.executer.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryService {

    public static List<String> getClassFilePathList(String targetPath, String packageName) throws Exception {
        // パッケージ名をパス形式に変換
        String packageDirectory = packageName.replaceAll("\\.", "/");
        try(Stream<Path> stream = Files.walk(Paths.get(targetPath))) {
            return stream.filter(path -> {
                String pathString = path.toString();
                // パス配下かつクラスファイル
                return pathString.contains(packageDirectory) && pathString.endsWith(".class");
            }).map(path -> path.toString()).collect(Collectors.toList());
        } catch(Exception e) {
            throw e;
        }
    }
}

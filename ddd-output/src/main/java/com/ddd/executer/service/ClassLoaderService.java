package com.ddd.executer.service;

import java.io.*;
import java.util.*;

public class ClassLoaderService extends ClassLoader {
    private static final int BUF_SIZE = 1024;
    public Class getClassFromFilePath(String targetPath) throws Exception {
        FileInputStream in = new FileInputStream(targetPath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[BUF_SIZE];
        int len = in.read(buf);
        while(len != -1) {
            out.write(buf, 0, len);
            len = in.read(buf);
        }
        byte[] loadedData = out.toByteArray();
        try {
            Class loadedCLass = defineClass(null, loadedData, 0, loadedData.length);
            return loadedCLass;
        } catch(LinkageError e) {
            return null;
        }
    }

    public Map<String, List<String>> getClassMethodCallMap(
            String targetPath,
            Map<String, String> fullNameClassPathMap,
            String ownClassName
    ) throws Exception {
        Map<String, List<String>> methodCallMap = new HashMap<>();
        ProcessBuilder pb = new ProcessBuilder(
                "javap", "-v", targetPath.replaceAll("\\.class", "")
        );
        Process p = pb.start();
        InputStream stdIn = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdIn));
        InputStream errIn = p.getErrorStream();
        boolean constantPoolStartFlag = false;
        try {
            String str = reader.readLine();
            while(str != null){
                str = reader.readLine();
                if(constantPoolStartFlag) {
                    String[] splitRow = Arrays.stream(str.split(" ")).filter(s-> s.length() > 0).toArray(String[]::new);
                    if (!splitRow[0].startsWith("#")) {
                        break;
                    }
                    if (splitRow.length >= 6) {
                        String type = splitRow[2];
                        if ("Methodref".equals(type) || "InterfaceMethodref".equals(type)) {
                            // クラスのフルネームを取得
                            String className = convertSlashToDot(splitRow[5].split("\\.")[0]);
                            if (fullNameClassPathMap.get(className) != null && !ownClassName.equals(className)) {
                                List<String> methodList = methodCallMap.get(className) != null ? methodCallMap.get(className) :new ArrayList();
                                // メソッド名を取得
                                String methodName = splitRow[5].substring(splitRow[5].indexOf(".") + 1, splitRow[5].indexOf(":"));
                                methodList.add(methodName);
                                methodCallMap.put(className, methodList);
                            }
                        }
                    }
                }
                if (str.equals("Constant pool:")) {
                    constantPoolStartFlag = true;
                }
            }
            int ret = p.waitFor();
            if (ret != 0) {
                throw new IOException("ret faliled");
            }
        } finally {
            reader.close();
            stdIn.close();
            errIn.close();
        }
        return methodCallMap;
    }

    // ファイルパスからパッケージを含むクラス名に変換
    public String getFullClassNameFromPath(String classFilePath, String packageName) {
        return convertSlashToDot(
                classFilePath.substring(
                        classFilePath.indexOf(packageName.replaceAll("\\.", "/")),
                        classFilePath.indexOf(".class")
                )
        );
    }

    // スラッシュをパッケージ用のドットに変換
    public String convertSlashToDot(String slashPath) {
        return slashPath.replace("/", ".");
    }

}

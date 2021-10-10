package sample;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapEntryTest {

    public static void main(String[] args) {
        // HashMapの初期化
        Map<Integer, String> testMap = Map.of(
                1, "test1",
                2, "test2",
                3, "test3"
        );
        System.out.println(testMap);
        // Entryへの変換
        Set<Entry<Integer, String>> testEntries = testMap.entrySet();
        System.out.println(testEntries);
        // HashMapへ戻す（Map.ofEntriesはJava9以降で使用可）
        Map<Integer, String> testMap2 = Map.ofEntries(testEntries.toArray(Entry[]::new));
        System.out.println(testMap2);
    }
}

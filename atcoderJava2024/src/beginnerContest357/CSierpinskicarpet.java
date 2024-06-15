package beginnerContest357;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CSierpinskicarpet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n == 0) {
            System.out.println("#");
            return;
        }

        // 初期のグリッドは一つだけ
        List<List<String>> beforeListList = new ArrayList<>();
        List<String> initialList = new ArrayList<>();
        initialList.add("#");
        beforeListList.add(initialList);

        int gridCount = 1;
        List<List<String>> resultListList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            gridCount = gridCount * 3;
            resultListList = new ArrayList<>();
            // 上段は前のやつを横に3個並べる
            for (List<String> beforeList : beforeListList) {
                List<String> addRecord = new ArrayList<>();
                for (int j1 = 0; j1 < 3; j1++) {
                    addRecord.addAll(beforeList);
                }
                resultListList.add(addRecord);
            }

            // 中断は前のやつを左右に2個並べる
            List<String> emptyRecord = new ArrayList<>();
            for (int j2 = 0; j2 < gridCount / 3; j2++) {
                emptyRecord.add(".");
            }
            for (List<String> beforeList : beforeListList) {
                List<String> addRecord = new ArrayList<>();
                addRecord.addAll(beforeList);
                addRecord.addAll(emptyRecord);
                addRecord.addAll(beforeList);
                resultListList.add(addRecord);
            }

            // 下段は前のやつを横に3個並べる
            for (List<String> beforeList : beforeListList) {
                List<String> addRecord = new ArrayList<>();
                for (int j3 = 0; j3 < 3; j3++) {
                    addRecord.addAll(beforeList);
                }
                resultListList.add(addRecord);
            }

            beforeListList = new ArrayList<>(resultListList);
        }

        System.out.println(resultListList.stream().map(rList ->
                rList.stream().collect(Collectors.joining(""))).collect(Collectors.joining("\n")));
    }
}

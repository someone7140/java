package beginnerContest358;

import java.util.*;

public class CPopcorn {
    static HashMap<Integer, Set<Integer>> alphabetIntMap;
    static Integer result;
    static Integer n;
    static Integer m;

    private static void loopFunc(
            int popcornIndex,
            Set<Integer> selectedStore,
            Integer tempResult) {
        var storeIndexSet = alphabetIntMap.get(popcornIndex);
        var findFlag = false;
        var notFindIndexList = new ArrayList<Integer>();

        for (int storeIndex : storeIndexSet) {
            if (selectedStore.contains(storeIndex)) {
                findFlag = true;
            } else {
                notFindIndexList.add(storeIndex);
            }
        }

        if (popcornIndex == m - 1) {
            if (!findFlag) {
                tempResult = tempResult + 1;
            }
            if (result == null || tempResult < result) {
                result = tempResult;
            }
        } else {
            if (findFlag) {
                loopFunc(popcornIndex + 1, selectedStore, tempResult);
            } else {
                for (int notFindIndex : notFindIndexList) {
                    selectedStore.add(notFindIndex);
                    loopFunc(popcornIndex + 1, selectedStore, tempResult + 1);
                    selectedStore.remove(notFindIndex);
                }
            }

        }
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        var aArrayArray = new String[n][m];
        for (int i = 0; i < n; i++) {
            aArrayArray[i] = sc.nextLine().split("");
        }

        // ポップコーン番号ごとの店番号のSet
        alphabetIntMap = new HashMap();
        for (int i = 0; i < m; i++) {
            var storeSet = new HashSet<Integer>();
            for (int j = 0; j < n; j++) {
                if (aArrayArray[j][i].equals("o")) {
                    storeSet.add(j);
                }
            }
            alphabetIntMap.put(i, storeSet);
        }

        var initialSet = new HashSet<Integer>();
        loopFunc(0, initialSet, 0);
        System.out.println(result);
    }
}

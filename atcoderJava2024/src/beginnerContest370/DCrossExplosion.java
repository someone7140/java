package beginnerContest370;

import java.util.*;

public class DCrossExplosion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int q = sc.nextInt();
        sc.nextLine();
        var hKeyTreeSetMap = new HashMap<Integer, TreeSet<Integer>>();
        var wKeyTreeSetMap = new HashMap<Integer, TreeSet<Integer>>();
        Integer[] hValueArray = java.util.stream.IntStream.range(1, h + 1).boxed().toArray(Integer[]::new);
        Integer[] wValueArray = java.util.stream.IntStream.range(1, w + 1).boxed().toArray(Integer[]::new);
        var result = h * w;

        // 縦のデータをMapに入れる
        for (int i = 1; i <= w; i++) {
            var hTreeSet = new TreeSet<>(Arrays.asList(hValueArray));
            wKeyTreeSetMap.put(i, hTreeSet);
        }
        // 横のデータをMapに入れる
        for (int i = 1; i <= h; i++) {
            var wTreeSet = new TreeSet<>(Arrays.asList(wValueArray));
            hKeyTreeSetMap.put(i, wTreeSet);
        }

        for (int i = 0; i < q; i++) {
            var rcArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
            var hValue = rcArray[0];
            var wValue = rcArray[1];

            // 縦の判定
            var hTreeSet = wKeyTreeSetMap.get(wValue);
            if (hTreeSet.contains(hValue)) {
                result = result - 1;
                hTreeSet.remove(hValue);
            } else {
                if (hValue > 1) {
                    var hLower = hTreeSet.lower(hValue);
                    if (hLower != null) {
                        hTreeSet.remove(hLower);
                        hKeyTreeSetMap.get(hLower).remove(wValue);
                        result = result - 1;
                    }
                }
                if (hValue < h) {
                    var hHigher = hTreeSet.higher(hValue);
                    if (hHigher != null) {
                        hTreeSet.remove(hHigher);
                        hKeyTreeSetMap.get(hHigher).remove(wValue);
                        result = result - 1;
                    }
                }

            }

            // 横の判定
            var wTreeSet = hKeyTreeSetMap.get(hValue);
            if (wTreeSet.contains(wValue)) {
                wTreeSet.remove(wValue);
            } else {
                if (wValue > 1) {
                    var wLower = wTreeSet.lower(wValue);
                    if (wLower != null) {
                        wTreeSet.remove(wLower);
                        wKeyTreeSetMap.get(wLower).remove(hValue);
                        result = result - 1;
                    }
                }
                if (wValue < w) {
                    var wHigher = wTreeSet.higher(wValue);
                    if (wHigher != null) {
                        wTreeSet.remove(wHigher);
                        wKeyTreeSetMap.get(wHigher).remove(hValue);
                        result = result - 1;
                    }
                }

            }

        }

        System.out.println(result);
    }
}

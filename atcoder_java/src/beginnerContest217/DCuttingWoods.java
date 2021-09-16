package beginnerContest217;

import java.util.*;
import java.util.stream.Collectors;

public class DCuttingWoods {
    static int l = 0;
    static List<Integer> resultList = new ArrayList<>();
    static TreeMap<Integer, Integer> rangeTreemap = new TreeMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int c = sc.nextInt();
            int x = sc.nextInt();
            if (c == 1) {
                split(x);
            } else {
                addResult(x);
            }
        }
        System.out.println(
                resultList.stream().map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator()))
        );
    }

    private static void addResult(int x) {
        if (rangeTreemap.isEmpty()) {
            resultList.add(l);
        } else {
            resultList.add(rangeTreemap.floorEntry(x - 1).getValue());
        }
    }

    private static void split(int x) {
        int targetIndex = x - 1;
        if (rangeTreemap.isEmpty()) {
            rangeTreemap.put(0, x);
            rangeTreemap.put(x, l - x);
        } else {
            Map.Entry<Integer, Integer> entry = rangeTreemap.floorEntry(targetIndex);
            int before = targetIndex - entry.getKey() + 1;
            int after = entry.getValue() - before;
            rangeTreemap.put(entry.getKey(), before);
            rangeTreemap.put(x, after);
        }
    }
}

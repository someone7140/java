package beginnerContest213;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DTakahashiTour {
    static Map<Integer, Boolean> houmonMap = new HashMap<>();
    static Map<Integer, List<Integer>> abSortedListMap = new HashMap<>();
    static List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Set<Integer>> abSetMap = new HashMap<>();
        for (int i = 0; i < (n - 1); i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            houmonMap.put(a, false);
            houmonMap.put(b, false);

            Set<Integer> aSet = abSetMap.get(a);
            if (aSet == null) {
                abSetMap.put(a, Set.of(b));
            } else {
                abSetMap.put(a, Stream.concat(aSet.stream(), Set.of(b).stream())
                        .collect(Collectors.toSet()));
            }
            Set<Integer> bSet = abSetMap.get(b);
            if (bSet == null) {
                abSetMap.put(b, Set.of(a));
            } else {
                abSetMap.put(b, Stream.concat(bSet.stream(), Set.of(a).stream())
                        .collect(Collectors.toSet()));
            }
        }

        for (int i = 1; i <= n; i++) {
            Set<Integer> abSet = abSetMap.get(i);
            List<Integer> abSortedList = abSet.stream().sorted().collect(Collectors.toList());
            abSortedListMap.put(i, abSortedList);
        }
        resultList.add(1);
        houmonMap.put(1, true);
        tansaku(1, 1);

        Collections.reverse(resultList);
        System.out.println(
                String.join(" ", resultList.stream().map(r -> String.valueOf(r)).collect(Collectors.toList()))
        );
    }
    static void tansaku(int targetIndex, int referResultIndex) {
        List<Integer> sortedList = abSortedListMap.get(targetIndex);
        if (sortedList.isEmpty()) {
            if (targetIndex == 1) {
                return;
            } else {
                int next = resultList.get(referResultIndex);
                resultList.add(0, next);
                tansaku(next, referResultIndex + 2);
            }
        } else {
            int next = sortedList.get(0);
            sortedList.remove(0);
            abSortedListMap.put(targetIndex, sortedList);
            if (houmonMap.get(next)) {
                tansaku(targetIndex,  referResultIndex);
            } else {
                houmonMap.put(next, true);
                resultList.add(0, next);
                tansaku(next,  1);
            }
        }
    }
}

package beginnerContest206;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DKAIBUNsyo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] aArray = new int[n];

        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextInt();
        }
        if (n == 1) {
            System.out.println(0);
            return;
        }
        int nHalf = n / 2;
        // 前半の配列を切り出し
        int[] beforeAArray = Arrays.copyOfRange(aArray, 0, nHalf);
        // 後半部分を切り出してreverse
        int afterStartIndex = 0;
        if (n % 2 == 0) {
            afterStartIndex = nHalf;
        } else {
            afterStartIndex = nHalf + 1;
        }
        List<Integer> tempAAfterList = Arrays.stream(Arrays.copyOfRange(aArray, afterStartIndex, n))
                .boxed().collect(Collectors.toList());
        Collections.reverse(tempAAfterList);
        int[] afterAArray = tempAAfterList.stream().mapToInt(a -> a).toArray();
        Map<Integer, Set<Integer>> chigauMap = new HashMap<>();
        Map<Integer, Integer> henkanMap = new HashMap<>();
        for (int i = 0; i < nHalf; i++) {
            if (beforeAArray[i] != afterAArray[i]) {
                int min = beforeAArray[i] < afterAArray[i] ? beforeAArray[i] : afterAArray[i];
                int max = beforeAArray[i] < afterAArray[i] ? afterAArray[i] : beforeAArray[i];
                Set<Integer> aList = chigauMap.get(min);
                if (aList == null) {
                    chigauMap.put(min, Set.of(max));
                } else {
                    aList = Stream.concat(aList.stream(), Set.of(max).stream())
                            .collect(Collectors.toSet());
                    chigauMap.put(min, aList);
                }
            }
        }
        long result = 0;
        for (Map.Entry<Integer, Set<Integer>> chigauEntry : chigauMap.entrySet()) {
            Integer key = chigauEntry.getKey();
            Set<Integer> values = chigauEntry.getValue();
            for (Integer value : values) {
                Integer getValue = henkanMap.get(value);
                if (getValue == null) {
                    result++;
                    henkanMap.put(value, key);
                }
            }

        }
        System.out.println(result);
    }
}

package beginnerContest429;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class COddOneSubsequence {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());
        var aCountMap = new HashMap<Integer, Long>();

        for (int i = 0; i < n; i++) {
            var a = aList.get(i);
            var count = aCountMap.get(a);
            if (count == null) {
                aCountMap.put(a, 1L);
            } else {
                aCountMap.put(a, count + 1);
            }
        }

        var result = 0L;
        for (var elem : aCountMap.entrySet()) {
            var count = elem.getValue();
            if (count >= 2) {
                // 2つの組み合わせ
                var comb = (count * (count - 1)) / 2;
                result = result + comb * (n - count);
            }
        }
        System.out.println(result);
    }
}

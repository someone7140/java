package beginnerContest361;

import java.util.*;
import java.util.stream.Collectors;

public class CMakeThemNarrow {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        List<Integer> aListSorted = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).sorted().collect(Collectors.toList());

        int startIndex = 0;
        int remainCount = n - k;
        int result = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0; i < n; i++) {
            count = count + 1;
            if (count < remainCount) {
                // 何もしない
            } else {
                int tempResult = aListSorted.get(i) - aListSorted.get(startIndex);
                if (tempResult == 0) {
                    result = tempResult;
                    break;
                } else if (tempResult < result) {
                    result = tempResult;
                }
                startIndex = startIndex + 1;
            }
        }

        System.out.println(result);

    }
}

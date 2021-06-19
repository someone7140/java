package beginnerContest205;

import java.util.*;

public class DKthExcluded {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        long min = 999999999999999999L;
        long max = -1L;
        List<Long> results = new ArrayList<>();
        long[] aArray = new long[n];
        long[] indexArray = new long[n];

        for (int i = 0; i < n; i++) {
            long a = sc.nextLong();
            if (min > a) {
                min = a;
            }
            if (max < a) {
                max = a;
            }
            aArray[i] = a;
            if (i == 0) {
                indexArray[i] = a - 1;
            } else {
                indexArray[i] = indexArray[i - 1] + (a - aArray[i - 1] - 1);
            }
        }


        for (int i = 0; i < q; i++) {
            long k = sc.nextLong();
            if (k < min) {
                results.add(k);
            } else if (k > indexArray[n - 1]) {
                results.add(
                  max + (k - indexArray[n - 1])
                );
            } else {
                int tempMinIndex = 0;
                int tempMaxIndex = n - 1;
                while (true) {
                    if (tempMinIndex + 1== tempMaxIndex) {
                        break;
                    } else {
                        int half = (tempMinIndex + tempMaxIndex) / 2;
                        if (k >= indexArray[tempMinIndex] && indexArray[half] >= k) {
                            tempMaxIndex = half;
                        } else {
                            tempMinIndex = half;
                        }
                    }
                }
                results.add(
                        aArray[tempMinIndex] + (k - indexArray[tempMinIndex])
                );
            }
        }
        results.stream().forEach(r -> {
            System.out.println(r);
        });
    }
}

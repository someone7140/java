package regularContest105;

import java.util.*;

public class BMAXmin {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] nArray = new long[n];
        for (int i = 0; i < n; i++) {
          nArray[i] = sc.nextLong();
        }
        nArray = Arrays.stream(nArray).distinct().toArray();
        while (nArray.length > 1) {
            long[] calcArray = calc(nArray);
            nArray = Arrays.stream(calcArray).distinct().toArray();
        }
        System.out.println(nArray[0]);
    }

    private static long[] calc(long[] nArray) {
        Map<Long, List<Long>> calcMap = new HashMap<>();
        long min = Arrays.stream(nArray).min().orElse(0);
        long[] returnArray =  Arrays.stream(nArray).map(n -> {
          if (n == min) {
            return n;
          } else {
              long amari = n % min;
              if (amari == 0) {
                  return min;
              } else {
                  return amari;
              }
          }
        }).toArray();
        return returnArray;
    }
}

package beginnerContest365;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class CTransportationExpenses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        sc.nextLine();
        Long[] aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Long.parseLong(a)).toArray(Long[]::new);

        var aArraySorted = Arrays.stream(aArray).sorted().toArray(Long[]::new);
        long defaultSum = 0L;
        TreeSet<Long> ts = new TreeSet<>();
        HashMap<Long, Integer> aSortedMinIndexMap = new HashMap();
        var ruisekiSum = new long[n];

        for (int i = 0; i < n; i++) {
            var mapGet = aSortedMinIndexMap.getOrDefault(aArraySorted[i], null);
            if (mapGet == null) {
                aSortedMinIndexMap.put(aArraySorted[i], i);
            }
            ts.add(aArraySorted[i]);

            if (i == 0) {
                ruisekiSum[i] = aArraySorted[i];
            } else {
                ruisekiSum[i] = ruisekiSum[i - 1] + aArraySorted[i];
            }
            defaultSum = defaultSum + aArraySorted[i];
        }

        if (defaultSum <= m) {
            System.out.println("infinite");
            return;
        }

        // まずは普通に割る
        var result = m / n;
        var loopFlag = true;
        // 仮結果より大きいもので最小のindex
        var higherValue = ts.higher(result);
        var minIndex = aSortedMinIndexMap.get(higherValue);
        var tempSumMin = minIndex == 0 ? 0L : ruisekiSum[minIndex - 1];
        var tempSum = tempSumMin + (n - minIndex) * result;
        var plusFlag = tempSum <= m;

        while (loopFlag) {
           if (plusFlag) {
               var tempResult = result + 1;
               var loopFlag2 = true;
               while (loopFlag2 && minIndex < n) {
                   if (aArraySorted[minIndex] <= tempResult) {
                       tempSumMin = tempSumMin + tempResult;
                       minIndex = minIndex + 1;
                   } else {
                       loopFlag2 = false;
                   }
               }
               tempSum = tempSumMin + (n - minIndex) * tempResult;
               if (tempSum > m) {
                   loopFlag = false;
               } else {
                   result = tempResult;
               }
           } else {
               var tempResult = result - 1;
               var loopFlag2 = true;
               while (loopFlag2 && minIndex < n) {
                   if (aArraySorted[minIndex] <= tempResult) {
                       tempSumMin = tempSumMin + tempResult;
                       minIndex = minIndex + 1;
                   } else {
                       loopFlag2 = false;
                   }
               }
               tempSum = tempSumMin + (n - minIndex) * tempResult;
               if (tempSum <= m) {
                   result = tempResult;
                   loopFlag = false;
               } else {
                   result = tempResult;
               }
           }
        }

        System.out.println(result);
    }

}

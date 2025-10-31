package beginnerContest429;

import java.util.*;
import java.util.stream.Collectors;

public class DOnAtCoderConference {
    static Integer n;
    static Long m;
    static Long c;
    static Long[] countArray;
    static List<Long> sumList;

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextLong();
        c = sc.nextLong();
        sc.nextLine();
        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());
        var countArrayTemp = new Long[n];

        for (int i = 0; i < n; i++) {
            var a = aList.get(i);
            var index = a == 0 ? n - 1 : a - 1;
            var count = countArrayTemp[index];
            if (count == null) {
                countArrayTemp[index] = 1L;
            } else {
                countArrayTemp[index] = count + 1L;
            }
        }

        countArray = new Long[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            var count = i >= n ? countArrayTemp[i - n] : countArrayTemp[i];
            if (count == null) {
                countArray[i] = 0L;
            } else {
                countArray[i] = count;
            }
        }

        var tempSum = 0L;
        var end = 0;
        sumList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                end = -1;
            } else {
                tempSum = tempSum - countArray[i - 1];
            }
            while (tempSum < c) {
                end = end + 1;
                tempSum = tempSum + countArray[end];
            }
            if (i == 0) {
                sumList.add(tempSum);
            } else {
                sumList.add(tempSum + sumList.get(i - 1));
            }
        }

        var result = 0L;
        var lastSum = sumList.get(n - 1);
        var syou = m / n;
        var amari = (int)(m % n);
        result = result + syou * lastSum;
        if (amari > 0) {
            result = result + sumList.get(amari - 1);
        }

        System.out.println(result);
    }
}

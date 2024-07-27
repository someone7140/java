package beginnerContest364;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

public class CMinimumGlutton {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long x = sc.nextLong();
        long y = sc.nextLong();
        sc.nextLine();
        Long[] aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Long.parseLong(a)).toArray(Long[]::new);
        Long[] bArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Long.parseLong(a)).toArray(Long[]::new);
        var aReverseList = Arrays.stream(aArray).sorted(reverseOrder()).collect(Collectors.toList());
        var bReverseList = Arrays.stream(bArray).sorted(reverseOrder()).collect(Collectors.toList());
        var result = Long.MAX_VALUE;

        var aTemp = 0L;
        var aCount = 0;
        for (Long a : aReverseList) {
            aCount = aCount + 1;
            aTemp = aTemp + a;
            if (aTemp > x) {
                break;
            }
        }
        result = aCount;

        var bTemp = 0L;
        var bCount = 0;
        for (Long b : bReverseList) {
            bCount = bCount + 1;
            bTemp = bTemp + b;
            if (bTemp > y) {
                break;
            }
        }
        if (bCount < result) {
            result = bCount;
        }

        System.out.println(result);
    }
}

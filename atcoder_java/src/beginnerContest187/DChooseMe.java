package beginnerContest187;

import java.util.Arrays;
import java.util.Scanner;

public class DChooseMe {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = 0;
        long aokiSum = 0;
        long takahashiSum = 0;
        long[][] abArray = new long[n][3];
        for (int i = 0; i < n; i++) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            aokiSum = aokiSum + a;
            abArray[i][0] = a;
            abArray[i][1] = b;
            abArray[i][2] = 2 * a + b;
        }
        Arrays.sort(abArray, (ab1, ab2) -> {
            if (ab2[2] > ab1[2]) {
                return 1;
            } else if (ab2[2] == ab1[2]) {
                return 0;
            } else {
                return -1;
            }
        });
        for (int i = 0; i < n; i++) {
            long[] ab = abArray[i];
            takahashiSum = takahashiSum + ab[0] + ab[1];
            aokiSum = aokiSum - ab[0];
            result++;
            if (takahashiSum > aokiSum) {
                break;
            }
        }
        System.out.println(result);
    }
}

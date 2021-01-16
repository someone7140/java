package keyence2021;

import java.util.Arrays;
import java.util.Scanner;

public class ATwoSequences2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long aMax = -1L;
        long bMax = -1L;
        long[] reslutArray = new long [n];

        long aArray[] = new long [n];
        long bArray[] = new long [n];

        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextLong();
        }
        for (int i = 0; i < n; i++) {
            bArray[i] = sc.nextLong();
        }
        for (int i = 0; i < n; i++) {
            long a = aArray[i];
            long b = bArray[i];
            if (i == 0) {
                reslutArray[i] = a * b;
            } else {
                long tempMax = reslutArray[i-1];
                long tempAB = aMax * b;
                long tempAdd = a * b;
                long[] tempAttay = {tempMax, tempAB, tempAdd};
                reslutArray[i] = Arrays.stream(tempAttay).max().getAsLong();
            }
            aMax = aMax < a ? a : aMax;
            bMax = bMax < b ? b : bMax;
        }
        Arrays.stream(reslutArray).forEach(result-> {
            System.out.println(result);
        });
    }
}

package beginnerContest220;

import java.util.Scanner;

public class CLongSequence {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] aArray = new long[n];
        long aSum = 0;
        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextLong();
            aSum = aSum + aArray[i];
        }
        long xPlusOne = sc.nextLong() + 1;

        long syou = xPlusOne / aSum;
        long amari = xPlusOne % aSum;

        long temp = syou * aSum;
        long result = syou * n;
        if (amari == 0) {
            System.out.println(result);
        } else {
            for (int i = 0; i < n; i++) {
                temp = temp + aArray[i];
                if (temp >= xPlusOne) {
                    result = result + i + 1;
                    break;
                }
            }
            System.out.println(result);
        }
    }
}

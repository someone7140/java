package beginnerContest222;

import java.math.BigInteger;
import java.util.*;

public class DBetweenTwoArrays {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] aArray = new int[n];
        int[] bArray = new int[n];

        long result = 0;
        long tempResult = 0;

        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            bArray[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                tempResult = Math.abs(aArray[i] - bArray[i]) + 1;
            } else {
                int beforeA = aArray[i - 1];
                int beforeB = bArray[i - 1];

                if (beforeA < aArray[i] && beforeA < bArray[i]) {
                    result = result + Math.abs(aArray[i] - bArray[i]) + 1;
                } else {
                    result = result + Math.abs(beforeA - Math.max(aArray[i], bArray[i])) + 1;
                }

                if (beforeB < aArray[i] && beforeB < bArray[i]) {
                    result = result + Math.abs(aArray[i] - bArray[i]) + 1;
                } else {
                    result = result + Math.abs(beforeB - Math.max(aArray[i], bArray[i])) + 1;
                }
            }
            result = result % 998244353;
        }
        System.out.println(result);
    }

}

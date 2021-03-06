package beginnerContest194;

import java.util.Scanner;

public class CSquaredError {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] aArray = new long[n];
        long result = 0;
        long powSum = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long a = sc.nextLong();
            long powA = (long)Math.pow(a, 2);
            if (i != 0) {
                result += powA * i + powSum - 2 * a * sum;
            }
            powSum += powA;
            sum += a;
            aArray[i] = a;
        }
        System.out.println(result);
    }
}

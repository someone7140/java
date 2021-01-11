package regularContest111;

import java.util.Scanner;

public class ASimpleMath2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long result = 1;
        long n = sc.nextLong();
        long m = sc.nextLong();
        if (n > 10) {
            // 6での結果
            System.out.println((long)Math.pow(10, 4) / m % m);
            System.out.println((long)Math.pow(10, 5) / m % m);
            long result6 = (long)Math.pow(10, 6) / m % m;
            System.out.println(result6);
            System.out.println((long)Math.pow(10, 7) / m % m);
            System.out.println((long)Math.pow(10, 8) / m % m);
            System.out.println((long)Math.pow(10, 9) / m % m);
            System.out.println((long)Math.pow(10, 10) / m % m);
            System.out.println((long)Math.pow(10, 11) / m % m);
            long result12 = (long)Math.pow(10, 12) / m % m;
            System.out.println(result12);
            System.out.println((long)Math.pow(10, 13) / m % m);
            System.out.println((long)Math.pow(10, 14) / m % m);
            System.out.println((long)Math.pow(10, 15) / m % m);
        } else {
            result = (long)Math.pow(10, n) / m % m;
        }

        // System.out.println(result);
    }
}

package kajima2020;

import java.util.Scanner;

public class ARedundantRedundancy {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long result = 2;
        long n = sc.nextInt();
        for(long i = 3; i <= n; i++) {
            result = calcLcm(result, i);
        }
        System.out.println(result + 1);
    }

    // 最大公約数を求める。
    private static long calcGcd(long m, long n) {
        if (m <= 0 || n <= 0) {
            throw new IllegalArgumentException("Arguments must be 1 and over.");
        }
        if(m < n) {
            long tmp = m;
            m = n;
            n = tmp;
        }
        long remainder = 0;
        while ((remainder = m % n) != 0) {
            m = n;
            n = remainder;
        }
        return n;
    }

    // 最小公倍数を求める。
    private static long calcLcm(long m, long n) {
        return m * n / calcGcd(m, n);
    }
}

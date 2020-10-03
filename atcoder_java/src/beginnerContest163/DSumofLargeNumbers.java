package beginnerContest163;

import java.util.Scanner;

public class DSumofLargeNumbers {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();
        long WARI = 1000000007L;
        long result = 0L;

        for (long i = k; i <= n + 1; i++) {
            long l = sumN(0, i - 1);
            long r = sumN(n - i + 1, n);
            result = (result + r - l + 1) % WARI;
        }
        System.out.println(result);
    }

    private static long sumN(long l, long r) {
        return (l + r) * (r - l + 1) / 2;
    }
}

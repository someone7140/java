package panasonic2021;

import java.util.Scanner;

public class CComma {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long result = 0L;
        if (n < 1000000L && n >= 1000L) {
            result += (n - 1000L) + 1L;
        }
        if (n < 1000000000L && n >= 1000000L) {
            result += 999000L;
            result += ((n - 1000000L) + 1L) * 2L;
        }
        if (n < 1000000000000L && n >= 1000000000L) {
            result += 999000L;
            result += 999000000L * 2L;
            result += ((n - 1000000000000L) + 1L) * 3L;
        }
        if (n < 1000000000000000L && n >= 1000000000000L) {
            result += 999000L;
            result += 999000000L * 2L;
            result += 999000000000L * 3L;
            result += ((n - 1000000000000L) + 1L) * 4L;
        }
        if (n == 1000000000000000L) {
            result += 999000L;
            result += 999000000L * 2L;
            result += 999000000000L * 3L;
            result += 999000000000000L * 4L;
            result += 5L;
        }
        System.out.println(result);
    }
}

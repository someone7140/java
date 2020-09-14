package beginnerContest178;

import java.util.Scanner;

public class CUbiquity {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long WARI = 1000000000000000007L;
        if (n == 1) {
            System.out.println(0);
        } else {
            long result = 1L;
            for (long i = n - 2; i > 0; i--) {
                result = result * 10 % WARI;
            }
            result = result * n % WARI;
            result = result * (n - 1) % WARI;
            System.out.println(result);
        }
    }
}

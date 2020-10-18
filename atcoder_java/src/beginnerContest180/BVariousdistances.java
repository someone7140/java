package beginnerContest180;

import java.util.Scanner;

public class BVariousdistances {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long max = 0;
        long m = 0;
        long u = 0;
        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();
            long xAbs = Math.abs(x);
            m = m + xAbs;
            u = u + x * x;
            if (max < xAbs) {
                max = xAbs;
            }
        }
        System.out.println(m);
        System.out.println(Math.sqrt(u));
        System.out.println(max);
    }
}

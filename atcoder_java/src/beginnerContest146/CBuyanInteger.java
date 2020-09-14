package beginnerContest146;

import java.util.Scanner;

public class CBuyanInteger {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long x = sc.nextLong();

        long l = 0;
        long r = 1000000001L;
        long c = 0;
        while (r-l > 1L) {
            c = (l + r) / 2;
            long tempResult = a * c + b * String.valueOf(c).length();
            if (tempResult <= x) {
                l = c;
            } else {
                r = c;
            }
        }
        System.out.print(l);
    }
}

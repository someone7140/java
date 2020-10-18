package beginnerContest180;

import java.util.Scanner;

public class DTakahashiUnevolved {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        long y = sc.nextLong();
        long a = sc.nextLong();
        long b = sc.nextLong();
        if (x >= y) {
            System.out.println(0);
        } else {
            long result = 0L;
            long wari = b / x;
            long kake = 0L;
            if (a < y) {
                while(true) {
                    long tempKake = kake == 0L ? a : kake * a;
                    if(tempKake > wari) {
                        break;
                    } else {
                        kake = tempKake;
                        result++;
                    }
                }
            }
            if (b < y) {
                long minus = kake == 0L ? x : kake;
                long amari = (y - minus) % b;
                long wari2 = amari == 0L ? (y - minus) / b - 1 : (y - minus) / b;
                if (wari2 > 0) {
                    result = result + wari2;
                }
            }
            System.out.println(result);
        }

    }
}

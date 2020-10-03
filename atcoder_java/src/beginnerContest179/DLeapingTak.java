package beginnerContest179;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DLeapingTak {
    static long WARI = 998244353L;
    static long result = 0L;
    static List<Long> lrList = new ArrayList<>();
    static long n = 0L;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextLong();
        long k = sc.nextLong();

        for (long i = 1; i <= k; i++ ) {
            long l = sc.nextLong();
            long r = sc.nextLong();

            if (l == r) {
                lrList.add(l);
            } else {
                for (long j = l; j <= r; j++) {
                    lrList.add(j);
                }
            }
        }
        resultCount(1);
        System.out.println(result);
    }

    private static void resultCount(long initial) {
        if (initial == n) {
            result = (result + 1) % WARI;
        } else if (initial < n) {
            for (long lr : lrList) {
                resultCount(initial + lr);
            }
        }
    }
}

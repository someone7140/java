package beginnerContest155;

import java.util.*;

public class DPairs {
    public static void main(String[] args){
        long INF = 1000000000000000001L;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] aArray = new long[n];
        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextLong();
        }
        long l = -INF;
        long r = INF;
        aArray = Arrays.stream(aArray).sorted().toArray();
        while (l + 1 < r) {
            long x = (l + r) / 2L;
            long tot = 0L;
            boolean ok = false;

            for (int i = 0; i < n; i++) {
                int l2 = -1;
                int r2 = n;
                if (aArray[i] < 0L) {
                    while (l2 + 1 < r2) {
                        int c = (l2 + r2) / 2;
                        if (aArray[i] * aArray[c] < x) {
                            r2 = c;
                        } else {
                            l2 = c;
                        }
                    }
                    tot += n - r2;
                } else {
                    while (l2 + 1 < r2) {
                        int c = (l2 + r2) / 2;
                        if (aArray[i] * aArray[c] < x) {
                            l2 = c;
                        } else {
                            r2 = c;
                        }
                    }
                    tot += r2;
                }
                if(aArray[i] * aArray[i] < x) {
                    tot--;
                }
            }
            tot = tot / 2;
            if (tot < k) {
                ok = true;
            } else {
                ok = false;
            }
            if (ok) {
                l = x;
            } else {
                r = x;
            }
        }
        System.out.println(l);
    }
}

package beginnerContest220;

import java.util.Scanner;

public class DFGoperation {
    static long[] aArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static long WARI = 998244353;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            if (i == 0) {
                aArray[a] = 1;
            } else {
                calc(a);
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(aArray[i]);
        }
    }

    private static void calc(int a) {
        long[] aArrayUpdated = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < 10; i++) {
            if (aArray[i] != 0) {
                // fの計算
                long tempF = (i + a) % 10;
                aArrayUpdated[(int)tempF] = (aArrayUpdated[(int)tempF] + aArray[i]) % WARI;

                // gの計算
                long tempG = (i * a) % 10;
                aArrayUpdated[(int)tempG] = (aArrayUpdated[(int)tempG] + aArray[i]) % WARI;
            }
        }
        aArray = aArrayUpdated;
    }
}

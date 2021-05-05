package beginnerContest198;

import java.util.Scanner;

public class CCompassWalking {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long r = sc.nextLong();
        long x = sc.nextLong();
        long y = sc.nextLong();
        // ユークリッド距離2乗
        long euclideanMulti = x * x + y * y;
        long rMulti = r * r;
        // 割り算
        long wariMulti = euclideanMulti / rMulti;
        long amariMulti = euclideanMulti % rMulti;
        if (wariMulti == 0) {
            System.out.println(2);
        } else {
            double wariSqrt = Math.sqrt((double)wariMulti);
            long wariSqrtLong = (long)wariSqrt;
            if (amariMulti != 0) {
                System.out.println(wariSqrtLong + 1);
            } else {
                if (wariSqrtLong == wariSqrt) {
                    System.out.println(wariSqrtLong);
                } else {
                    System.out.println(wariSqrtLong + 1);
                }
            }
        }
    }
}

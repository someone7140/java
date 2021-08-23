package beginnerContest208;

import java.util.Scanner;

public class BFactorialYenCoin {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long p = sc.nextLong();
        long[] kaijouArray = new long[10];
        kaijouArray[0] = 3628800;
        kaijouArray[1] = 362880;
        kaijouArray[2] = 40320;
        kaijouArray[3] = 5040;
        kaijouArray[4] = 720;
        kaijouArray[5] = 120;
        kaijouArray[6] = 24;
        kaijouArray[7] = 6;
        kaijouArray[8] = 2;
        kaijouArray[9] = 1;

        int result = 0;
        long pTemp = p;

        for (int i = 0; i < 10; i++) {
            if (kaijouArray[i] <= pTemp) {
                result++;
                long tempKaijou = kaijouArray[i];
                while (tempKaijou <= pTemp) {
                    long tempKaijou2 = tempKaijou + kaijouArray[i];
                    if (tempKaijou2 <= pTemp) {
                        tempKaijou = tempKaijou2;
                        result++;
                    } else {
                        break;
                    }
                }
                pTemp = pTemp - tempKaijou;
                if (pTemp == 0) {
                  break;
                }
            }
        }
        System.out.println(result);
    }
}

package sompo2021;

import java.math.BigInteger;
import java.util.Scanner;

public class DBasen {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        BigInteger M = new BigInteger(sc.nextLine());
        // BigInteger temp = new BigInteger("");
        char[] xCharArray = x.toCharArray();
        int xSize = xCharArray.length;
        int d = 0;
        for (int i = 0; i < xSize; i++) {
            int temp = Character.getNumericValue(xCharArray[i]);
            if (d < temp) {
                d = temp;
            }
        }
        d++;
        long result = 0;
        boolean loopFlag = true;
        while (loopFlag) {
            BigInteger tempBig = new BigInteger("0");
            BigInteger tempBig2 = new BigInteger("0");
            // 逆から変換
            for (int i = xSize - 1; i >= 0; i--) {
                // 累乗を計算していく
                if(i == xSize - 1) {
                    tempBig2 = new BigInteger("1");
                } else {
                    tempBig2 = tempBig2.multiply(new BigInteger(String.valueOf(d)));
                }
                String xStr = String.valueOf(xCharArray[i]);
                if (!xStr.equals("0")) {
                    tempBig = tempBig.add(tempBig2.multiply(new BigInteger(String.valueOf(xStr))));
                    if (tempBig.compareTo(M) == 1) {
                        loopFlag = false;
                        break;
                    }

                }
            }
            if (loopFlag) {
                d++;
                result++;
            }
        }
        System.out.println(result);
    }

}

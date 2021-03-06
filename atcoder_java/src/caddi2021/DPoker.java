package caddi2021;

import java.util.Scanner;

public class DPoker {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long k  = sc.nextLong();
        sc.nextLine();
        String sStr = sc.nextLine();
        String tStr = sc.nextLine();
        long [] maisuuArray= new long[10];
        for (int i = 0; i < 10; i++) {
            maisuuArray[i] = k;
        }
        int [] sArray = new int[10];
        char[] sCharArray = sStr.toCharArray();
        for (int i = 0; i < sCharArray.length - 1; i++) {
            int s = Character.getNumericValue(sCharArray[i]);
            sArray[s - 1] = sArray[s - 1] + 1;
            maisuuArray[s - 1] = maisuuArray[s - 1] - 1;
        }
        int [] tArray = new int[10];
        char[] tCharArray = tStr.toCharArray();
        for (int i = 0; i < tCharArray.length - 1; i++) {
            int t = Character.getNumericValue(tCharArray[i]);
            tArray[t - 1] = tArray[t - 1] + 1;
            maisuuArray[t - 1] = maisuuArray[t - 1] - 1;
        }
        long bunshi = 0;
        long bunbo = (k*9 - 8) * (k*9 - 9);
        for (int i = 0; i < 10; i++) {
            long temp = maisuuArray[i];
            if (temp != 0) {
                for (int j = 0; j < 10; j++) {
                    if (i == j) {
                        if (temp != 1) {
                            if (takahashiWin(sArray.clone(), i, tArray.clone(), j)) {
                                bunshi += temp * (temp -1);
                            }
                        }
                    } else {
                        long temp2 = maisuuArray[j];
                        if (temp2 != 0) {
                            if (takahashiWin(sArray.clone(), i, tArray.clone(), j)) {
                                bunshi += temp * temp2;
                            }
                        }
                    }
                }
            }
        }
        System.out.println((double)bunshi / (double)bunbo);
    }
    public static boolean takahashiWin(int [] sArray, int s, int [] tArray, int t) {
        sArray[s] = sArray[s] + 1;
        long sTensuu = tennsuuCalc(sArray);
        tArray[t] = tArray[t] + 1;
        long tTensuu = tennsuuCalc(tArray);
        return sTensuu > tTensuu;
    }
    public static long tennsuuCalc(int[] tefudaArray) {
        long calc = 0;
        for (int i = 0; i < tefudaArray.length; i++) {
            calc += (i + 1) * Math.pow(10, tefudaArray[i]);
        }
        System.out.println(tefudaArray);
        System.out.println(calc);
        return calc;
    }
}

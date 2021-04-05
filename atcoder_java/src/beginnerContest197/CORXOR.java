package beginnerContest197;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CORXOR {
    static long result = 999999999999999999L;
    static long tempResult = 99999999999999L;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] aArray = new long[n];
        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextLong();
        }
        List<long[]> aArrayList = new ArrayList<>();
        aArrayList.add(aArray);
        calc(aArrayList);
        System.out.println(result);
    }

    private static void calc(List<long[]> aArrayList) {
        int listSize = aArrayList.size();
        long[] orResult = new long[listSize];
        if (aArrayList.size() != 1) {
            List<long[]> aArrayList2 = new ArrayList<>();
            for (int i = 0; i < listSize; i++) {
                if (i == listSize - 1) {
                    long[] arrayA = aArrayList.get(i);
                    if (arrayA.length > 1) {
                        int n = arrayA.length;
                        for (int j = 0; j < n - 1; j++) {
                            List<long[]> aArrayList3 = new ArrayList<>(aArrayList2);
                            long[] aArray1 = Arrays.copyOfRange(arrayA, 0, j + 1);
                            long[] aArray2 = Arrays.copyOfRange(arrayA, j + 1, n);
                            aArrayList3.add(aArray1);
                            aArrayList3.add(aArray2);
                            calc(aArrayList3);
                        }

                    }
                    aArrayList2.add(arrayA);
                    orResult[i] = getOr(arrayA);
                    tempResult = getXOr(orResult);
                    if (tempResult < result) {
                        result = tempResult;
                    }
                } else {
                    long[] arrayA = aArrayList.get(i);
                    aArrayList2.add(arrayA);
                    orResult[i] = getOr(arrayA);
                }
            }
        } else {
            long[] aArray = aArrayList.get(0);
            int n = aArray.length;
            for (int i = 0; i < n - 1; i++) {
                List<long[]> aArrayList2 = new ArrayList<>();
                long[] aArray1 = Arrays.copyOfRange(aArray, 0, i + 1);
                long[] aArray2 = Arrays.copyOfRange(aArray, i + 1, n);
                aArrayList2.add(aArray1);
                aArrayList2.add(aArray2);
                calc(aArrayList2);
            }
        }
    }

    private static long getOr(long[] aArraySplit) {
        long orResult = 0;
        for (int i = 0; i < aArraySplit.length; i++) {
            orResult = orResult | aArraySplit[i];
        }
        return orResult;
    }

    private static long getXOr(long[] aArraySplit) {
        long xorResult = aArraySplit[0];
        for (int i = 1; i < aArraySplit.length; i++) {
            xorResult = xorResult ^ aArraySplit[i];
        }
        return xorResult;
    }
}

package beginnerContest194;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BJobAssignment {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int aMin = 99999999;
        int bMin = 99999999;
        int aMin2 = 99999999;
        int bMin2 = 99999999;
        int[] aArray = new int[n];
        int[] bArray = new int[n];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            aArray[i] = a;
            bArray[i] = b;
            if (a < aMin) {
                aMin2 = aMin;
                aMin = a;
            }
            if(b < bMin) {
                bMin2 = bMin;
                bMin = b;
            }
        }
        Map<Integer, Integer> tempAMap= new HashMap<>();
        Map<Integer, Integer> tempBMap= new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (aArray[i] <= aMin2) {
                tempAMap.put(i, aArray[i]);
            }
            if (bArray[i] <= bMin2) {
                tempBMap.put(i, bArray[i]);
            }
        }
        AtomicInteger result = new AtomicInteger(99999999);
        tempAMap.forEach((index, a) -> {
            tempBMap.forEach((index2, b) -> {
                int tempResult = 99999999;
                if (index == index2) {
                    tempResult = a + b;
                } else {
                    tempResult = Math.max(a , b);
                }
                if (tempResult < result.get()) {
                    result.set(tempResult);
                }
            });
        });
        System.out.println(result.get());
    }
}

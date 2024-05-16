package beginnerContest331;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSumofNumbersGreaterThanMe {
    static class AItem {
        int index;
        long amount;

        AItem(int index, long amount) {
            this.index = index;
            this.amount = amount;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String aStr = sc.nextLine();
        Long[] aArray = Arrays.stream(aStr.split(" ")).map(a -> Long.parseLong(a)).toArray(Long[]::new);
        Map<Long, Integer> aMap = new HashMap<>();
        AItem[] aItemArray = new AItem[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            AItem a = new AItem(i, aArray[i]);
            aItemArray[i] = a;
            Integer count = aMap.get(aArray[i]);
            if (count == null) {
                aMap.put(aArray[i], 1);
            } else {
                aMap.put(aArray[i], count + 1);
            }
            sum = sum + aArray[i];
        }

        Long[] keys = aMap.keySet().toArray(Long[]::new);
        Arrays.sort(keys);
        long tempSum = sum;
        Map<Long, Long> aRuisekiMap = new HashMap<>();
        for (long k : keys) {
            int count = aMap.get(k);
            tempSum = tempSum - count * k;
            aRuisekiMap.put(k, tempSum);
        }
        String[] resultArray = new String[n];

        for (int i = 0; i < n; i++) {
            resultArray[i] = aRuisekiMap.get(aArray[i]).toString();
        }
        System.out.println(String.join(" ", resultArray));
    }
}

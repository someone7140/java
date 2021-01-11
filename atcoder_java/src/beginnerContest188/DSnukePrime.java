package beginnerContest188;

import java.util.*;

public class DSnukePrime {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Map<Integer, Long> amountMap = new HashMap();
        int n = sc.nextInt();
        long prime = sc.nextLong();

        for(int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            Long tempAmount1 = amountMap.get(a);
            if (tempAmount1 == null) {
                amountMap.put(a, c);
            } else {
                amountMap.put(a, tempAmount1 + c);
            }
            Long tempAmount2 = amountMap.get(b + 1);
            if (tempAmount2 == null) {
                amountMap.put(b + 1, -c);
            } else {
                amountMap.put(b + 1, tempAmount2 - c);
            }
        }
        long sum = 0;
        int tempDay = -1;
        long tempSum = 0;
        amountMap = sortMapByKey(amountMap);
        for (Map.Entry<Integer, Long> amount : amountMap.entrySet()) {
            if (tempDay != -1) {
                long tempSum2 = tempSum > prime ? prime : tempSum;
                sum = sum + (amount.getKey() - tempDay -  1) * tempSum2;
            }
            tempSum = tempSum + amount.getValue();
            long plusAmount = tempSum > prime ? prime : tempSum;
            sum = sum + plusAmount;
            tempDay = amount.getKey();
        }
        System.out.println(sum);
    }

    private static LinkedHashMap<Integer, Long> sortMapByKey(Map<Integer, Long> map) {
        List<Map.Entry<Integer, Long>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, Comparator.comparing(Map.Entry::getKey));

        LinkedHashMap<Integer, Long> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Long> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}

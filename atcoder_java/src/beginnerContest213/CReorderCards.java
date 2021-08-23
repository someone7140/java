package beginnerContest213;

import java.util.*;

public class CReorderCards {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();
        Map<Integer, Integer> aIndexMap = new HashMap<>();
        Map<Integer, Integer> bIndexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            aMap.put(i, sc.nextInt());
            bMap.put(i, sc.nextInt());
        }
        List<Map.Entry<Integer, Integer>> aSortedList = new ArrayList<>(aMap.entrySet());
        aSortedList.sort(Map.Entry.comparingByValue());
        int index = 0;
        int previousValue = -1;
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> aEntry = aSortedList.get(i);
            if (previousValue != aEntry.getValue()) {
                index++;
            }
            previousValue =  aEntry.getValue();
            aIndexMap.put(aEntry.getKey(), index);
        }
        index = 0;
        previousValue = -1;
        List<Map.Entry<Integer, Integer>> bSortedList = new ArrayList<>(bMap.entrySet());
        bSortedList.sort(Map.Entry.comparingByValue());
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> bEntry = bSortedList.get(i);
            if (previousValue != bEntry.getValue()) {
                index++;
            }
            previousValue =  bEntry.getValue();
            bIndexMap.put(bEntry.getKey(), index);
        }
        for (int i = 0; i < n; i++) {
            int aIndex = aIndexMap.get(i);
            int bIndex = bIndexMap.get(i);
            System.out.println(aIndex + " " + bIndex);
        }
    }
}

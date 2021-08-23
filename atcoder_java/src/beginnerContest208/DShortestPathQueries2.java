package beginnerContest208;

import java.util.*;

public class DShortestPathQueries2 {
    static Map<String, Integer[]> saisyouMap = new HashMap<>();
    static Map<Integer, List<Integer[]>> mMap = new HashMap<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            List<Integer[]> valueList = mMap.get(a);
            if (valueList != null) {
                Integer[] saitan = new Integer[]{b, c};
                valueList.add(saitan);
                mMap.put(a, valueList);
            } else {
                valueList = new ArrayList<>();
                Integer[] saitan = new Integer[]{b, c};
                valueList.add(saitan);
                mMap.put(a, valueList);
            }
        }
        for (Integer key : mMap.keySet()) {
            List<Integer[]> valueList = mMap.get(key);
            for (Integer[] valueArray : valueList) {
                setSaisyou(key, valueArray[0], valueArray[1], -1);
            }
        }
        long result = 0;
        for (Integer[] valueArray : saisyouMap.values()) {
            if (valueArray[1] == -1) {
                result += valueArray[0] * n;
            } else {
                result += valueArray[0] * (n - valueArray[1] + 1);
            }
        }
        System.out.println(result);
    }

    private static void setSaisyou(Integer start, Integer end, Integer cost, Integer keiro) {
        Integer[] saisyouValue = saisyouMap.get(start + "-" + end);
        if (saisyouValue == null) {
            saisyouMap.put(start + "-" + end, new Integer[]{cost, keiro});
            List<Integer[]> valueList = mMap.get(end);
            if (valueList != null) {
                for (Integer[] valueArray : valueList) {
                    setSaisyou(start, valueArray[0], valueArray[1] + cost, end > keiro ? end : keiro);
                }
            }
        } else {
            if (saisyouValue[0] > cost) {
                saisyouMap.put(start + "-" + end, new Integer[]{cost, keiro});
                List<Integer[]> valueList = mMap.get(end);
                if (valueList != null) {
                    for (Integer[] valueArray : valueList) {
                        setSaisyou(start, valueArray[0], valueArray[1] + cost, end > keiro ? end : keiro);
                    }
                }
            } else if (saisyouValue[0] == cost) {
                if (keiro < saisyouValue[1]) {
                    saisyouMap.put(start + "-" + end, new Integer[]{cost, keiro});
                    List<Integer[]> valueList = mMap.get(end);
                    if (valueList != null) {
                        for (Integer[] valueArray : valueList) {
                            setSaisyou(end, valueArray[0], valueArray[1] + cost, end > keiro ? end : keiro);
                        }
                    }
                }
            }
        }

    }
}

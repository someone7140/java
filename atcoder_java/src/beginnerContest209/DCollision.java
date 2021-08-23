package beginnerContest209;

import java.util.*;

public class DCollision {
    static Map<Integer, List<Integer>> douroMap = new HashMap<>();
    static Map<Integer, Map<Integer, List<Integer>>> keiroMap = new HashMap<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            List<Integer> douroList1 = douroMap.get(a);
            if (douroList1 == null) {
                douroList1 = new ArrayList<>();
            }
            douroList1.add(b);
            douroMap.put(a, douroList1);
            Map<Integer, List<Integer>> keiroMap1 = keiroMap.get(a);
            List<Integer> keiroList1 = new ArrayList<>();
            keiroList1.add(a);
            keiroList1.add(b);
            if (keiroMap1 == null) {
                keiroMap1 = new HashMap<>();
                keiroMap1.put(b, keiroList1);
            } else {
                keiroMap1.put(b, keiroList1);
            }
            keiroMap.put(a, keiroMap1);
        }
        for (Map.Entry<Integer, List<Integer>> douro : douroMap.entrySet()) {
            List <Integer> values = douro.getValue();
            for (int i = 0; i < values.size(); i++) {
                Integer value = values.get(i);
                List<Integer> keiroList = new ArrayList<>();
                keiroList.add(douro.getKey());
                keiroList.add(value);
                Map<Integer, List<Integer>> keiroMap1 = keiroMap.get(douro.getKey());
                childTansa(keiroMap1, keiroList, douro.getKey(), value);
            }
        }
        for (int i = 0; i < q; i++) {

        }
        // System.out.println(result);
    }

    private static void childTansa(
            Map<Integer, List<Integer>> keiroMap1, List<Integer> keiroList, Integer start, Integer next
    ) {
        Map<Integer, List<Integer>> keiroMap2 = keiroMap.get(next);
        if (keiroMap2 == null) {
            return;
        } else {
            for (Map.Entry<Integer, List<Integer>> keiroMap3 : keiroMap2.entrySet()) {
                keiroList.add(keiroMap3.getKey());
                List<Integer> startList = keiroMap1.get(keiroMap3.getKey());
                if (startList == null) {
                    keiroMap1.put(keiroMap3.getKey(), keiroList);
                    for (Integer value3 : keiroMap3.getValue()) {
                        if (start != value3) {
                            childTansa(keiroMap1, keiroList, start, value3);
                        }
                    }
                } else {
                    if (startList.size() <= keiroList.size()) {
                        return;
                    } else {
                        keiroMap1.put(keiroMap3.getKey(), keiroList);
                        for (Integer value3 : keiroMap3.getValue()) {
                            if (start != value3) {
                                childTansa(keiroMap1, keiroList, start, value3);
                            }
                        }
                    }
                }
            }
        }
    }
}

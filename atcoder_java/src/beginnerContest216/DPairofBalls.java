package beginnerContest216;

import java.util.*;

public class DPairofBalls {
    static int n = 0;
    static int m = 0;
    static int toridashiCount = 0;
    static Map<Integer, List<List<Integer>>> aMap = new HashMap<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int k = sc.nextInt();
            List<Integer> aList = new ArrayList();
            int aFirst = 0;
            for (int j = 0; j < k; j++) {
                if (j == 0) {
                    aFirst = sc.nextInt();
                    aList.add(aFirst);
                } else {
                    aList.add(sc.nextInt());
                }
            }
            List<List<Integer>> values = aMap.get(aFirst);
            if (values == null) {
                values = new ArrayList<>();
                values.add(aList);
                aMap.put(aFirst, values);
            } else {
                values.add(aList);
                aMap.put(aFirst, values);
            }

        }
        boolean loopFlag = true;
        while (loopFlag) {
            if (toridashiCount == n) {
                loopFlag = false;
                break;
            } else {
                boolean findFlag = false;
                for (Map.Entry<Integer, List<List<Integer>>> aMapEntry : aMap.entrySet() ) {
                    if (aMapEntry.getValue().size() > 1) {
                        findFlag = true;
                        mapUpdate(aMapEntry.getKey());
                        break;
                    }
                }
                if(!findFlag) {
                    loopFlag = false;
                    break;
                }
            }
        }
        if (toridashiCount == n) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static void mapUpdate(Integer key) {
        List<List<Integer>> listList = aMap.get(key);
        if (listList.size() > 1) {
            aMap.put(key, null);
            toridashiCount = toridashiCount + 1;
            List<Integer> list1 = listList.get(0);
            List<Integer> list2 = listList.get(1);
            list1.remove(0);
            if (list1.size() > 0) {
                Integer list1First = list1.get(0);
                List<List<Integer>> list1List = aMap.get(list1First);
                if (list1List == null) {
                    list1List = new ArrayList<>();
                    list1List.add(list1);
                    aMap.put(list1First, list1List);
                } else {
                    list1List.add(list1);
                    aMap.put(list1First, list1List);
                    mapUpdate(list1First);
                }
            }
            list2.remove(0);
            if (list2.size() > 0) {
                Integer list2First = list2.get(0);
                List<List<Integer>> list2List = aMap.get(list2First);
                if (list2List == null) {
                    list2List = new ArrayList<>();
                    list2List.add(list2);
                    aMap.put(list2First, list2List);
                } else {
                    list2List.add(list2);
                    aMap.put(list2First, list2List);
                    mapUpdate(list2First);
                }
            }
        }
    }
}

package beginnerContest360;

import java.util.*;

public class CMoveIt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Integer[] aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
        Integer[] wArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);

        var aMap = new HashMap<Integer, TreeSet<Integer>>();
        var aMultiSet = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            var a = aArray[i];
            var w = wArray[i];
            if (aMap.containsKey(a)) {
                var aRegistered = aMap.get(a);
                aRegistered.add(w);
                aMap.put(a, aRegistered);
                aMultiSet.add(a);
            } else {
                var aSet = new TreeSet<Integer>();
                aSet.add(w);
                aMap.put(a, aSet);
            }
        }

        Long result = 0L;
        for (int i = 1; i <= n; i++) {
            if (!aMap.containsKey(i)) {
                // 複数入ってるキーから任意のを取得
                var size = aMultiSet.size();
                if (size > 0) {
                    var multiKey = aMultiSet.toArray()[0];
                    var mutiSet = aMap.get(multiKey);
                    var idou = mutiSet.first();

                    // 移動
                    var newSet = new TreeSet<Integer>();
                    newSet.add(idou);
                    aMap.put(i, newSet);
                    result = result + idou.longValue();

                    // 元のを削除
                    mutiSet.remove(idou);
                    if (mutiSet.size() == 1) {
                        aMultiSet.remove(multiKey);
                    }
                }

            }
        }

        System.out.println(result);
    }
}

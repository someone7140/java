package beginnerContest371;

import java.util.*;
import java.util.stream.Collectors;

public class CMakeIsomorphic {
    static Map<Integer, Set<Integer>> gMap = new HashMap<>();
    static Map<Integer, Set<Integer>> hMap = new HashMap<>();
    static List<List<Integer>> hCostList = new ArrayList<>();
    static long result = -1;
    static Integer[] gValueArray;
    static List<Integer[]> permutationList = new ArrayList<>();

    private static void permutation(Integer[] seed) {
        Integer[] perm = new Integer[seed.length];
        boolean[] used = new boolean[seed.length];
        buildPerm(seed, perm, used, 0);
    }

    private static void buildPerm(Integer[] seed, Integer[] perm, boolean[] used, int index) {
        if (index == seed.length) {
            permutationList.add(perm.clone());
        }

        for (int i = 0; i < seed.length; i++) {
            if (used[i])
                continue;
            perm[index] = seed[i];
            used[i] = true;
            buildPerm(seed, perm, used, index + 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        int mg = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < mg; i++) {
            var uvArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);

            var uSet = gMap.getOrDefault(uvArray[0], new HashSet<>());
            uSet.add(uvArray[1]);
            gMap.put(uvArray[0], uSet);

            var vSet = gMap.getOrDefault(uvArray[1], new HashSet<>());
            vSet.add(uvArray[0]);
            gMap.put(uvArray[1], vSet);
        }

        int mh = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < mh; i++) {
            var abArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);

            var aSet = hMap.getOrDefault(abArray[0], new HashSet<>());
            aSet.add(abArray[1]);
            hMap.put(abArray[0], aSet);

            var bSet = hMap.getOrDefault(abArray[1], new HashSet<>());
            bSet.add(abArray[0]);
            hMap.put(abArray[1], bSet);
        }

        for (int i = 0; i < n - 1; i++) {
            var aList = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).collect(Collectors.toList());
            hCostList.add(aList);
        }

        gValueArray = java.util.stream.IntStream.range(1, n + 1).boxed().toArray(Integer[]::new);
        permutation(gValueArray.clone());
        /*
        for (Integer[] hValueArray : permutationList) {
            long tempResult = 0;
            for (int i = 0; i < n - 1; i++) {
                var gValue = i + 1;
                var gSet = gMap.getOrDefault(gValue, new HashSet<>());
                // あるべきhSet
                var newHSet = new HashSet<Integer>();
                for (Integer gSetValue : gSet) {
                    newHSet.add(hValueArray[gSetValue - 1]);
                }
                var hValue = hValueArray[i];
                var nowHSet = hMap.getOrDefault(hValue, new HashSet<>());
                // 削除
                for (Integer hSetValue : nowHSet) {
                    if (i != hSetValue - 1) {
                        var min = i < hSetValue - 1 ? i : hSetValue - 1;
                        var max = i < hSetValue - 1 ? hSetValue - 1 : i;
                        if (!newHSet.contains(hSetValue)) {
                            tempResult = tempResult + hCostList.get(min).get(max - min - 1);
                        }
                    }

                }
                // 追加
                for (Integer hSetValue : newHSet) {
                    if (i != hSetValue - 1) {
                        var min = i < hSetValue - 1 ? i : hSetValue - 1;
                        var max = i < hSetValue - 1 ? hSetValue - 1 : i;
                        if (!nowHSet.contains(hSetValue)) {
                            tempResult = tempResult + hCostList.get(min).get(max - min - 1);
                        }
                    }
                }
            }

            if (result == -1) {
                result = tempResult;
            } else if (tempResult < result) {
                result = tempResult;
            }
        }
*/

        long tempResult = 0;
        Integer[] hValueArray = {3, 0, 1, 4, 2};
        for (int i = 0; i < n - 1; i++) {
            var gValue = i + 1;
            var gSet = gMap.getOrDefault(gValue, new HashSet<>());
            // あるべきhSet
            var newHSet = new HashSet<Integer>();
            for (Integer gSetValue : gSet) {
                newHSet.add(hValueArray[gSetValue - 1] + 1);
            }
            var hValue = hValueArray[i];
            var nowHSet = hMap.getOrDefault(hValue + 1, new HashSet<>());
            // 削除
            for (Integer hSetValue : nowHSet) {
                if (hValue != hSetValue - 1) {
                    var min = hValue < hSetValue - 1 ? hValue : hSetValue - 1;
                    var max = hValue < hSetValue - 1 ? hSetValue - 1 : hValue;
                    if (!newHSet.contains(hSetValue)) {
                        tempResult = tempResult + hCostList.get(min).get(max - min - 1);
                    }
                }

            }
            // 追加
            for (Integer hSetValue : newHSet) {
                if (hValue != hSetValue - 1) {
                    var min = hValue < hSetValue - 1 ? hValue : hSetValue - 1;
                    var max = hValue < hSetValue - 1 ? hSetValue - 1 : hValue;
                    if (!nowHSet.contains(hSetValue)) {
                        tempResult = tempResult + hCostList.get(min).get(max - min - 1);
                    }
                }
            }
        }

        if (result == -1) {
            result = tempResult;
        } else if (tempResult < result) {
            result = tempResult;
        }

        System.out.println(result < 0 ? 0 : result);
    }
}

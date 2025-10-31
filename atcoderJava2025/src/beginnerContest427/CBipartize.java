package beginnerContest427;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CBipartize {

    static Map<Integer, List<Integer>> pointMap;
    static Map<Integer, List<Integer>> visitedMap;
    static Set<String> visitedSet;

    static void tansaku(Integer from, List<Integer> starts, Integer flag) {
        var nextFlag = flag == 0 ? 1 : 0;
        for (var start : starts) {
            var hen = from + "-" + start;
            if (!visitedSet.contains(hen)) {
                visitedSet.add(hen);
                var flagList = visitedMap.get(start);
                if (flagList == null) {
                    visitedMap.put(start, Arrays.asList(flag));
                } else {
                    var newList = Stream.concat(
                                    flagList.stream(), Arrays.asList(flag).stream())
                            .collect(Collectors.toList());
                    visitedMap.put(start, newList);
                }

                var nexts = pointMap.get(start);
                if (nexts != null) {
                    tansaku(start, nexts, nextFlag);
                }
            }

        }
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var m = sc.nextInt();
        sc.nextLine();

        pointMap = new HashMap<>();
        visitedMap = new HashMap<>();
        visitedSet = new HashSet<>();

        for (int i = 0; i < m; i++) {
            var uv = sc.nextLine().split(" ");
            var u = Integer.parseInt(uv[0]);
            var v = Integer.parseInt(uv[1]);

            var endList = pointMap.get(u);
            if (endList == null) {
                pointMap.put(u, Arrays.asList(v));
            } else {
                pointMap.put(u, Stream.concat(endList.stream(), Arrays.asList(v).stream())
                        .collect(Collectors.toList()));
            }
        }

        visitedMap.put(1, Arrays.asList(0));
        tansaku(1, pointMap.get(1), 1);

        var result = 0;
        for (var entry : visitedMap.entrySet()) {
            var zeroCount = 0;
            var oneCount = 0;
            for (var flag : entry.getValue()) {
                if (flag == 0) {
                    zeroCount = zeroCount + 1;
                } else {
                    oneCount = oneCount + 1;
                }
            }
            if (zeroCount > 0 && oneCount > 0) {
                result = result + (zeroCount > oneCount ? oneCount : zeroCount);
            }
        }

        System.out.println(result);
    }
}

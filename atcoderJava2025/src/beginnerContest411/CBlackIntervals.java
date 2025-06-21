package beginnerContest411;

import java.util.*;
import java.util.stream.Collectors;

public class CBlackIntervals {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var q = sc.nextInt();
        sc.nextLine();
        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        var pointMap =  new HashMap<Integer, String>();
        var resultList = new ArrayList<String>();
        var tempResult = 0;

        for (int a : aList) {
            var pointInfo = pointMap.get(a);
            var beforeA = a - 1;
            var beforePointInfo = pointMap.get(beforeA);
            var afterA = a + 1;
            var afterPointInfo = pointMap.get(afterA);

            if (pointInfo == null) {
                if (beforePointInfo == null && afterPointInfo == null) {
                    tempResult = tempResult + 1;
                    pointMap.put(a, "start");
                } else if (beforePointInfo != null && afterPointInfo != null) {
                    pointMap.put(a, "middle");
                    if (beforePointInfo.equals("end")) {
                        pointMap.put(beforeA, "middle");
                    }
                    if (afterPointInfo.equals("start")) {
                        pointMap.put(afterA, "middle");
                    }
                    tempResult = tempResult - 1;
                } else if (beforePointInfo != null ) {
                    pointMap.put(a, "end");
                    if (beforePointInfo.equals("end")) {
                        pointMap.put(beforeA, "middle");
                    }
                } else if (afterPointInfo != null ) {
                    pointMap.put(a, "start");
                    if (afterPointInfo.equals("start")) {
                        pointMap.put(afterA, "end");
                    }
                }
            } else {
                pointMap.remove(a);
                if (beforePointInfo == null && afterPointInfo == null) {
                    tempResult = tempResult - 1;
                } else if (beforePointInfo != null && afterPointInfo != null) {
                    if (beforePointInfo.equals("middle")) {
                        pointMap.put(beforeA, "end");
                    }
                    if (afterPointInfo.equals("middle")) {
                        pointMap.put(afterA, "start");
                    }
                    tempResult = tempResult + 1;
                } else if (beforePointInfo != null ) {
                    if (beforePointInfo.equals("middle")) {
                        pointMap.put(beforeA, "end");
                    }
                } else if (afterPointInfo != null ) {
                    if (afterPointInfo.equals("end")) {
                        pointMap.put(afterA, "start");
                    }
                }
            }

            resultList.add(String.valueOf(tempResult));
        }

        System.out.println(String.join("\n", resultList));
    }
}

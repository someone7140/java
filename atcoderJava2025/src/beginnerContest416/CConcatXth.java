package beginnerContest416;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CConcatXth {

    static class SPoint {
        public String text;
        public int point;

        public SPoint(String text, int point) {
            this.text = text;
            this.point = point;
        }
    }

    static class PointIndexList {
        public int point;
        public List<Integer> indexList;

        public PointIndexList(int point, List<Integer> indexList) {
            this.point = point;
            this.indexList = indexList;
        }
    }

    static int n;
    static int k;
    static int x;
    static List<SPoint> sPointList;
    static List<PointIndexList> pointIndexList;

    static void loop(int loopLevel, Integer beforeIndex, PointIndexList beforePointIndexList) {
        loopLevel = loopLevel + 1;
        for (int i = 0; i < n; i++) {
            PointIndexList beforePointIndexList2;
            if (beforePointIndexList == null) {
                beforePointIndexList2 = new PointIndexList(0, Arrays.asList());
            } else {
                beforePointIndexList2 = beforePointIndexList;
            }
            if (k > loopLevel) {
                loop(loopLevel, i, new PointIndexList(
                        sPointList.get(i).point + beforePointIndexList2.point,
                        Stream.concat(Arrays.asList(i).stream(), beforePointIndexList2.indexList.stream())
                                .collect(Collectors.toList())));
            } else {
                var addPoint = new PointIndexList(
                        sPointList.get(i).point + beforePointIndexList2.point,
                        Stream.concat(Arrays.asList(i).stream(), beforePointIndexList2.indexList.stream())
                                .collect(Collectors.toList()));
                pointIndexList.add(addPoint);
            }
        }
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        x = sc.nextInt();
        sc.nextLine();
        var sList = new ArrayList<String>();
        sPointList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            sList.add(sc.nextLine());
        }
        Collections.sort(sList);

        var point = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sList.get(i).equals(sList.get(i - 1))) {
                sPointList.add(new SPoint(sList.get(i), point));
            } else {
                point = point + 1;
                sPointList.add(new SPoint(sList.get(i), point));
            }
        }
        pointIndexList = new ArrayList<>();


        loop(0, null, null);
        Collections.sort(
                pointIndexList,
                new Comparator<PointIndexList>() {
                    @Override
                    public int compare(PointIndexList obj1, PointIndexList obj2) {
                        return obj1.point - obj2.point;
                    }
                }
        );
        var resultIndexList = pointIndexList.get(x - 1);
        var resultIndexLen = resultIndexList.indexList.size();
        var result = "";
        for (int i = 0; i < resultIndexLen; i++) {
            result = result + sList.get(resultIndexList.indexList.get(i));
        }
        System.out.println(result);
    }
}

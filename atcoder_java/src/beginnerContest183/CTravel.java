package beginnerContest183;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CTravel {
    static List<Integer> kyotenList = new ArrayList<>();
    static List<Long> resultList = new ArrayList<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();

        long[][] ttArray = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ttArray[i][j] = sc.nextLong();
            }
            kyotenList.add(i);
        }
        addResult(ttArray, 0, new ArrayList<>(), 0);
        System.out.println(resultList.stream().filter(r -> r == k).collect(Collectors.toList()).size());

    }

    private static void addResult(long[][] ttArray, int nowPos, List<Integer> visitedArray, long cost) {
        List<Integer> filteringList = kyotenList.stream().filter(k -> !visitedArray.contains(k) && k != nowPos).collect(Collectors.toList());
        if (filteringList.isEmpty()) {
            resultList.add(cost + ttArray[nowPos][0]);
        } else {
            for (int kyoten : filteringList) {
                List<Integer> newVisitedArray = new ArrayList<>(visitedArray);
                newVisitedArray.add(kyoten);
                addResult(ttArray, kyoten, newVisitedArray, cost + ttArray[nowPos][kyoten]);
            }
        }

    }
}

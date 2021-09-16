package beginnerContest217;

import java.util.*;
import java.util.stream.Collectors;

public class ESortingQueries {
    static LinkedList<Integer> xList = new LinkedList<>();
    static List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int c = sc.nextInt();
            if (c == 1) {
                xList.add(sc.nextInt());
            } else if (c == 2) {
                resultList.add(xList.pollFirst());
            } else {
                Collections.sort(xList);
            }
        }
        System.out.println(
                resultList.stream().map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator()))
        );
    }

}

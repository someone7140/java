package beginnerContest356;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;

public class ASubsegmentReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        List<Integer> resultList = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i + 1 >= l && i + 1 <= r) {
                tempList.add(i + 1);
            }
            if (i + 1 == r) {
                tempList = tempList.stream().sorted(reverseOrder()).collect(Collectors.toList());
                resultList = Stream.concat(resultList.stream(), tempList.stream()).collect(Collectors.toList());
            }

            if (i + 1 < l || i + 1 > r) {
                resultList.add(i + 1);
            }
        }

        System.out.println(resultList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}

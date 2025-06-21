package beginnerContest368;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ACut {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var k = sc.nextInt();
        sc.nextLine();

        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        var aListFirst = aList.subList(n-k, n);
        var aListLast = aList.subList(0, n-k);

        var resultList = Stream.concat(aListFirst.stream(), aListLast.stream())
                .map(a -> a.toString()).collect(Collectors.toList());

        System.out.println(String.join(" ", resultList));
    }
}

package beginnerContest415;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BPickTwo {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var sList = Arrays.stream(sc.nextLine().split("")).collect(Collectors.toList());

        var count = 0;
        var index = 1;
        var before = 0;
        var after = 1;
        var resultList = new ArrayList<String>();

        for (var s : sList) {
            if (s.equals("#")) {
                if (count % 2 == 0) {
                    before = index;
                } else {
                    after = index;
                    var result = before + "," + after;
                    resultList.add(result);
                }
                count = count + 1;
            }

            index = index + 1;
        }

        System.out.println(String.join("\n", resultList));
    }
}

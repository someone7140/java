package beginnerContest411;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BDistanceTable {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var dList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        var resultList = new ArrayList<String>();
        for (int i = 0; i < n - 1; i++) {
            var tempResult = new ArrayList<Integer>();
            var tempSum = 0;
            for (int j = i; j < n - 1; j++) {
                tempSum = tempSum + dList.get(j);
                tempResult.add(tempSum);
            }

            resultList.add(String.join(" ", tempResult.stream().map(a -> a.toString()).collect(Collectors.toList())));
        }

        System.out.println(String.join("\n", resultList));
    }
}

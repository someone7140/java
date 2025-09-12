package beginnerContest417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BSearchandDelete {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var m = sc.nextInt();
        sc.nextLine();
        var aList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());
        var bList = Arrays.stream(sc.nextLine().split(" "))
                .map(a -> Integer.parseInt(a)).collect(Collectors.toList());

        for (int b : bList) {
            int index = -1;
            for (int i = 0; i < aList.size(); i++) {
                if (b == aList.get(i)) {
                    index = i;
                    break;
                }
            }
            if (index > -1) {
                aList.remove(index);
            }
        }

        if (aList.size() > 0) {
            var resultList = new ArrayList<String>();
            for (int a : aList) {
                resultList.add(String.valueOf(a));
            }
            System.out.println(String.join(" ", resultList));
        }
    }
}

package beginnerContest361;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BIntesectionofCuboids {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] aArray = sc.nextLine().split(" ");
        String[] bArray = sc.nextLine().split(" ");
        List<Integer> aStart = new ArrayList<>();
        List<Integer> aEnd = new ArrayList<>();
        List<Integer> bStart = new ArrayList<>();
        List<Integer> bEnd = new ArrayList<>();

        int index = 0;
        for (String a : aArray) {
            if (index < 3) {
                aStart.add(Integer.parseInt(a));
            } else {
                aEnd.add(Integer.parseInt(a));
            }
            index = index + 1;
        }
        index = 0;
        for (String b : bArray) {
            if (index < 3) {
                bStart.add(Integer.parseInt(b));
            } else {
                bEnd.add(Integer.parseInt(b));
            }
            index = index + 1;
        }

        String result = "Yes";

        if (((aStart.get(0) < bStart.get(0) && aEnd.get(0) > bStart.get(0))
                || (aStart.get(0) < bEnd.get(0) && aEnd.get(0) > bEnd.get(0)) ||
                (aStart.get(0) == bStart.get(0) && aEnd.get(0) == bEnd.get(0))) ||
                ((bStart.get(0) < aStart.get(0) && bEnd.get(0) > aStart.get(0))
                        || (bStart.get(0) < aEnd.get(0) && bEnd.get(0) > aEnd.get(0)) ||
                        (bStart.get(0) == aStart.get(0) && bEnd.get(0) == aEnd.get(0))
                )
        ) {
            // 何もしない
        } else {
            result = "No";
        }
        if (((aStart.get(1) < bStart.get(1) && aEnd.get(1) > bStart.get(1))
                || (aStart.get(1) < bEnd.get(1) && aEnd.get(1) > bEnd.get(1)) ||
                (aStart.get(1) == bStart.get(1) && aEnd.get(1) == bEnd.get(1))) ||
                ((bStart.get(1) < aStart.get(1) && bEnd.get(1) > aStart.get(1))
                        || (bStart.get(1) < aEnd.get(1) && bEnd.get(1) > aEnd.get(1)) ||
                        (bStart.get(1) == aStart.get(1) && bEnd.get(1) == aEnd.get(1))
                )
        ) {
            // 何もしない
        } else {
            result = "No";
        }
        if (((aStart.get(2) < bStart.get(2) && aEnd.get(2) > bStart.get(2))
                || (aStart.get(2) < bEnd.get(2) && aEnd.get(2) > bEnd.get(2)) ||
                (aStart.get(2) == bStart.get(2) && aEnd.get(2) == bEnd.get(2))) ||
                ((bStart.get(2) < aStart.get(2) && bEnd.get(2) > aStart.get(2))
                        || (bStart.get(2) < aEnd.get(2) && bEnd.get(2) > aEnd.get(2)) ||
                        (bStart.get(2) == aStart.get(2) && bEnd.get(2) == aEnd.get(2))
                )
        ) {
            // 何もしない
        } else {
            result = "No";
        }

        System.out.println(result);

    }
}

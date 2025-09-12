package beginnerContest421;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CAlternated {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var s = sc.nextLine();

        var seikaiAListIndex1 = new ArrayList<Integer>();
        var seikaiBListIndex1 = new ArrayList<Integer>();
        var seikaiAListIndex2 = new ArrayList<Integer>();
        var seikaiBListIndex2 = new ArrayList<Integer>();

        var sAlistIndex = new ArrayList<Integer>();
        var sBListIndex = new ArrayList<Integer>();

        var sList = s.split("");

        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 0) {
                seikaiAListIndex1.add(i);
                seikaiBListIndex2.add(i);
            } else {
                seikaiBListIndex1.add(i);
                seikaiAListIndex2.add(i);
            }
            var sWord = sList[i];
            if (sWord.equals("A")) {
                sAlistIndex.add(i);
            } else {
                sBListIndex.add(i);
            }
        }

        var result1_1 = 0L;
        var result1_2 = 0L;
        var result2_1 = 0L;
        var result2_2 = 0L;
        for (int i = 0; i < n; i++) {
            result1_1 = result1_1 + Math.abs(sAlistIndex.get(i) - seikaiAListIndex1.get(i));
            result1_2 = result1_2 + Math.abs(sBListIndex.get(i) - seikaiBListIndex1.get(i));
            result2_1 = result2_1 + Math.abs(sBListIndex.get(i) - seikaiBListIndex2.get(i));
            result2_2 = result2_2 + Math.abs(sAlistIndex.get(i) - seikaiAListIndex2.get(i));
        }
        ArrayList<Long> results = new ArrayList<Long>();
        results.add(result1_1);
        results.add(result1_2);
        results.add(result2_1);
        results.add(result2_2);
        System.out.println(Collections.min(results));
    }
}

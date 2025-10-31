package beginnerContest428;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BMostFrequentSubstrings {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var k = sc.nextInt();
        sc.nextLine();

        var s = sc.nextLine();
        var sArray = s.split("");
        var countMap = new HashMap<String, Integer>();
        var maxCount = 0;

        for (int i = 0; i < n - k + 1; i++) {
            var temp = sArray[i];
            for (int j = i + 1; j < i + k; j++) {
                temp = temp + sArray[j];
            }
            var count = countMap.get(temp);
            if (count == null) {
                count = 1;
            } else {
                count = count + 1;
            }
            countMap.put(temp, count);
            if (count > maxCount) {
                maxCount = count;
            }
        }

        var results = new ArrayList<String>();
        for (var elem : countMap.entrySet()) {
            if (elem.getValue() == maxCount) {
                results.add(elem.getKey());
            }
        }
        Collections.sort(results);

        System.out.println(maxCount);
        System.out.println(String.join(" ", results));
    }
}

package beginnerContest428;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class D183184 {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var t = sc.nextInt();
        sc.nextLine();
        var results = new ArrayList<String>();
        for (int i = 0; i < t; i++) {
            var queries = sc.nextLine().split(" ");
            var c = Long.parseLong(queries[0]);
            var d = Long.parseLong(queries[1]);
            var cd = Long.parseLong(queries[0] + String.valueOf(c + d));
            var limit = Long.parseLong(queries[0] + String.valueOf(c + 1));
            var tempResult = 0L;

            var isNext = true;
            var tempLimitD = Math.sqrt(cd);
            var tempLimitL = (long) tempLimitD;
            var heihouSet = new HashSet<String>();
            while (isNext) {
                var tempLimitL2 = tempLimitL * tempLimitL;
                if (tempLimitL2 < limit) {
                    isNext = false;
                    break;
                } else {
                    heihouSet.add(String.valueOf(tempLimitL2));
                }
                tempLimitL = tempLimitL - 1;
            }
            for (var heihou : heihouSet) {
                if (heihou.startsWith(queries[0])) {
                    var temp3 = heihou.replaceFirst(queries[0], "");
                    if (!temp3.startsWith("0")) {
                        tempResult = tempResult + 1;
                    }
                }
            }
            results.add(String.valueOf(tempResult));
        }

        System.out.println(String.join("\n", results));
    }
}

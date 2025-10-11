package beginnerContest426;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CUpgradeRequired {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var q = sc.nextInt();
        sc.nextLine();
        var vCountMap = new HashMap<Integer, Integer>();
        var vArray = new int[n];
        var resultList = new ArrayList<String>();

        for (int i = 1; i <= n; i++) {
            vCountMap.put(i, 1);
            vArray[i - 1] = 1;
        }

        var startIndex = 0;
        for (int i = 0; i < q; i++) {
            var xy = sc.nextLine().split(" ");
            var x = Integer.parseInt(xy[0]);
            var y = Integer.parseInt(xy[1]);

            var plusCount = 0;
            var deleteVersion = 0;
            for (int k = startIndex; k < n; k++) {
                var v = k + 1;
                if (v > x) {
                    break;
                } else {
                    var vCount = vCountMap.get(v);
                    if (vCount != null) {
                        plusCount = plusCount + vCount;
                        deleteVersion = deleteVersion + 1;
                    }
                }
            }
            startIndex = startIndex + deleteVersion;

            var vCount = vCountMap.get(y);
            if (vCount != null) {
                vCountMap.put(y, vCount + plusCount);
            }
            resultList.add(String.valueOf(plusCount));
        }

        System.out.println(String.join("\n", resultList));
    }
}

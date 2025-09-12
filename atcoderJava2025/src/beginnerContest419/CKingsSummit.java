package beginnerContest419;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CKingsSummit {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var xList = new ArrayList<Integer>();
        var yList = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            var queries = sc.nextLine().split(" ");
            var x = Integer.parseInt(queries[0]);
            var y = Integer.parseInt(queries[1]);
            xList.add(x);
            yList.add(y);
        }

        var minX = Collections.min(xList);
        var maxX = Collections.max(xList);
        var xSabun = maxX - minX;
        var minY = Collections.min(yList);
        var maxY = Collections.max(yList);
        var ySabun = maxY - minY;
        var result = 0;
        if (xSabun > ySabun) {
            var xWari =(minX + maxX) / 2;
            var sabun1 = xWari - minX;
            var sabun2 = maxX - xWari;
            result = sabun1 > sabun2 ? sabun1 : sabun2;
        } else {
            var yWari =(minY + maxY) / 2;
            var sabun1 = yWari - minY;
            var sabun2 = maxY - yWari;
            result = sabun1 > sabun2 ? sabun1 : sabun2;
        }

        System.out.println(result);
    }
}

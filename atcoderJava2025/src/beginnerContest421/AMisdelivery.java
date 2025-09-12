package beginnerContest421;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AMisdelivery {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var sList = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            sList.add(sc.nextLine());
        }
        var xy = sc.nextLine().split(" ");
        var x = Integer.parseInt(xy[0]);
        var y = xy[1];

        var target = sList.get(x - 1);
        System.out.println(y.equals(target) ? "Yes" : "No");
    }
}

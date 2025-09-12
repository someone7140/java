package beginnerContest421;

import java.util.ArrayList;
import java.util.Scanner;

public class BFibonacciReversed {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var xy = sc.nextLine().split(" ");
        var x = Long.parseLong(xy[0]);
        var y = Long.parseLong(xy[1]);

        var before = x;
        var result = y;
        for (int i = 0; i < 8; i++) {
            var tempBefore = result;
            var tempResult = result + before;
            StringBuilder sb = new StringBuilder();
            sb.append(tempResult);
            sb.reverse();

            result = Long.parseLong(sb.toString());
            before = tempBefore;
        }

        System.out.println(result);
    }
}

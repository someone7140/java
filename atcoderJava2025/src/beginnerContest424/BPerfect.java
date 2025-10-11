package beginnerContest424;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BPerfect {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var m = sc.nextInt();
        var k = sc.nextInt();
        sc.nextLine();

        var resultList = new ArrayList<String>();
        var seikaiMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < k; i++) {
            var queries = sc.nextLine().split(" ");
            var a = Integer.parseInt(queries[0]);
            var b = Integer.parseInt(queries[1]);
            var count = seikaiMap.get(a);
            if (count == null) {
                seikaiMap.put(a, 1);
            } else {
                seikaiMap.put(a, count + 1);
            }
            if (seikaiMap.get(a) == m) {
                resultList.add(String.valueOf(a));
            }
        }
        System.out.println(String.join(" ", resultList));
    }
}

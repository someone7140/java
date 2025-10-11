package beginnerContest426;

import java.util.HashMap;
import java.util.Scanner;

public class BTheOddOneOut {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var sArray = sc.nextLine().split("");
        var sMap = new HashMap<String, Integer>();
        for (String sMoji : sArray) {
            var s = sMap.get(sMoji);
            if (s == null) {
                sMap.put(sMoji, 1);
            } else {
                sMap.put(sMoji, s + 1);
            }
        }

        for (var sElem : sMap.entrySet()) {
            if (sElem.getValue() == 1) {
                System.out.println(sElem.getKey());
                return;
            }
        }
    }
}

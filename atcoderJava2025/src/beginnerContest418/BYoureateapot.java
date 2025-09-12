package beginnerContest418;

import java.util.Scanner;
import java.math.BigDecimal;

public class BYoureateapot {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var s = sc.nextLine();
        var sLen = s.length();

        var result = new BigDecimal(0);

        for (int i = 0; i < sLen - 1; i++) {
            var s1Word = String.valueOf(s.charAt(i));
            var totalCount = 1;
            var tCount = 1;

            if (s1Word.equals("t")) {
                for (int j = i + 1; j < sLen; j++) {
                    var s2Word = String.valueOf(s.charAt(j));
                    totalCount = totalCount + 1;
                    if (s2Word.equals(s1Word)) {
                        tCount = tCount + 1;
                        if (tCount > 2) {
                            var bunshi = new BigDecimal(String.valueOf(tCount - 2));
                            var bunbo = new BigDecimal(String.valueOf(totalCount - 2));
                            var tempResult = bunshi.divide(bunbo, 50, BigDecimal.ROUND_HALF_UP);
                            if (tempResult.compareTo(result) == 1) {
                                result = tempResult;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }
}

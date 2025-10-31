package beginnerContest427;

import java.util.Scanner;

public class BSumofDigitsSequence {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var result = 1L;

        for (int i = 0; i < n - 1; i++) {
            var resultStrs = String.valueOf(result).split("");
            var tempResult = 0L;
            for (var resultStr : resultStrs) {
                tempResult = tempResult + Long.parseLong(resultStr);
            }
            result = result + tempResult;
        }
        System.out.println(result);
    }
}

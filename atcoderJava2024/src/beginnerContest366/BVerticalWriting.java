package beginnerContest366;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BVerticalWriting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        String[] tArray = new String[n];
        var maxLength = 0;

        for (int i = 0; i < n; i++) {
            var t = sc.nextLine();
            var len = t.length();
            if (maxLength < len) {
                maxLength = len;
            }

            tArray[i] = t;
        }

        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            var tempResult = "";
            for (int j = n - 1; j >= 0; j--) {
                var t = tArray[j];
                var tLen = t.length();
                if (tLen >=  i + 1) {
                    tempResult = tempResult + t.charAt(i);
                } else {
                    tempResult = tempResult + "*";
                }
            }
            var loopFlag = true;
            while (loopFlag) {
                var tempLen = tempResult.length();
                var lastT = String.valueOf(tempResult.charAt(tempLen - 1));
                if (lastT.equals("*")) {
                    tempResult = tempResult.substring(0, tempLen - 1);
                } else {
                    loopFlag = false;
                }
            }
            resultList.add(tempResult);
        }

        System.out.println(String.join("\n", resultList));
    }
}

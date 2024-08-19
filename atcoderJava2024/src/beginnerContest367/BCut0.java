package beginnerContest367;

import java.util.Scanner;

public class BCut0 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        String result ="";
        var deleteFlag = true;
        var xChars = x.toCharArray();
        var xLen = xChars.length;
        for (int i = xLen - 1; i >= 0; i--) {
            var xVal = String.valueOf(xChars[i]);
            if (!deleteFlag) {
                result = xVal + result;
            } else {
                if (xVal.equals("0") || xVal.equals(".")) {
                    // 追加しない
                    if (xVal.equals(".")) {
                        deleteFlag = false;
                    }
                } else {
                    result = xVal + result;
                    deleteFlag = false;
                }
            }
        }

        if (result.equals("")) {
            System.out.println("0");
        } else {
            System.out.println(result);
        }
    }
}

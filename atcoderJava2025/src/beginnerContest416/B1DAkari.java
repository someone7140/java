package beginnerContest416;

import java.util.Scanner;

public class B1DAkari {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var sArray = sc.nextLine().split("");
        var result = "";
        var sLen = sArray.length;

        var startFlag = false;
        for (int i = 0; i < sLen; i++) {
            if (!startFlag) {
                if (sArray[i].equals(".")) {
                    result = result + "o";
                    startFlag = true;
                } else {
                    result = result + sArray[i];
                }
            } else {
                if (sArray[i].equals(".") && sArray[i - 1].equals("#")) {
                    result = result + "o";
                } else {
                    result = result + sArray[i];
                }
            }
        }

        System.out.println(result);
    }
}

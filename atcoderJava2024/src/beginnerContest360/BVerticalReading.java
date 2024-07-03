package beginnerContest360;

import java.util.Scanner;

public class BVerticalReading {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] st = sc.nextLine().split(" ");
        String s = st[0];
        String r = st[1];

        int sLen = s.length();
        String result = "No";
        var sCharArray = s.toCharArray();
        for (int i = 1; i < sLen; i++) {
            for (int j = 1; j <= i; j++) {
                int index = 0;
                String tempResult = "";
                String tempRenketsu = "";
                for (char sChar : sCharArray) {
                    tempRenketsu = tempRenketsu + sChar;
                    if ((index + 1) % i == 0) {
                        tempResult = tempResult + tempRenketsu.toCharArray()[j - 1];
                        tempRenketsu = "";
                    }
                    index = index + 1;
                }
                if (!tempRenketsu.equals("") && tempRenketsu.length() >= j) {
                    tempResult = tempResult + tempRenketsu.toCharArray()[j - 1];
                }
                if (tempResult.equals(r)) {
                    result = "Yes";
                    break;
                }
            }

            if (result.equals("Yes")) {
                break;
            }
        }

        System.out.println(result);
    }
}

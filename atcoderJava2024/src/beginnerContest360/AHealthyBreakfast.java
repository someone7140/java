package beginnerContest360;

import java.util.Scanner;

public class AHealthyBreakfast {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int rPos = 0;
        int mPos = 0;
        int nowPos = 0;

        for (char sChar : s.toCharArray()) {
            if (String.valueOf(sChar).equals("R")) {
                rPos = nowPos;
            }
            if (String.valueOf(sChar).equals("M")) {
                mPos = nowPos;
            }
            nowPos = nowPos + 1;
        }

        if (rPos < mPos) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }
}

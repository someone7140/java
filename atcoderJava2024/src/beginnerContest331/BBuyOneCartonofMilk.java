package beginnerContest331;

import java.util.Scanner;

public class BBuyOneCartonofMilk {
    static int result = Integer.MAX_VALUE;
    static int n = 0;
    static int s = 0;
    static int m = 0;
    static int l = 0;

    static void saiki_judge(int nowCount, int nowAmount) {
        if (nowCount >= n) {
            if (result >= nowAmount) {
                result = nowAmount;
            }
        } else {
            saiki_judge(nowCount + 6, nowAmount + s);
            saiki_judge(nowCount + 8, nowAmount + m);
            saiki_judge(nowCount + 12, nowAmount + l);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.nextInt();
        m = sc.nextInt();
        l = sc.nextInt();

        saiki_judge(0, 0);
        System.out.println(result);
    }
}

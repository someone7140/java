package unicorn2021;

import java.util.Scanner;

public class CCalendarValidator {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] bArray = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bArray[i][j] = sc.nextInt();
            }
        }

        String result = "Yes";
        // 左上のチェック
        int amari = bArray[0][0] % 7;
        if ((amari + m - 1) > 7) {
            result = "No";
        } else if (amari == 0 && m != 1) {
            result = "No";
        } else {
            for (int i = 0; i < n; i++) {
                if (result.equals("Yes")) {
                    for (int j = 0; j < m; j++) {
                        // 一番左から-1して7の倍数であるか
                        if (j != 0) {
                            if (bArray[i][j] != (bArray[i][j - 1] + 1)) {
                                result = "No";
                                break;
                            }
                        }
                        if (i != 0) {
                            // 上からプラス7か
                            if (bArray[i][j] != (bArray[i - 1][j] + 7)) {
                                result = "No";
                                break;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }
}

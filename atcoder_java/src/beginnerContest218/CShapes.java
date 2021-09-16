package beginnerContest218;

import java.util.Arrays;
import java.util.Scanner;

public class CShapes {
    static String[][] sArray;
    static String[][] tArray;
    static int n;
    static int[] NOTHING_LEFT_ABOVE = {-1, -1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sArray = new String[n][n];
        tArray = new String[n][n];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String sRow = sc.nextLine();
            char[] sCharArray = sRow.toCharArray();
            for (int j = 0; j < n; j++) {
                sArray[i][j] = String.valueOf(sCharArray[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            String tRow = sc.nextLine();
            char[] tCharArray = tRow.toCharArray();
            for (int j = 0; j < n; j++) {
                tArray[i][j] = String.valueOf(tCharArray[j]);
            }
        }
        String result = "No";

        for (int i = 0; i < 4; i++) {
            // まずは左上を取得
            int[] sLeftAbove = getLeftAbovePos(sArray);
            int[] tLeftAbove = getLeftAbovePos(tArray);
            if (Arrays.equals(sLeftAbove, tLeftAbove)) {
                // 比較
                if (Arrays.equals(sArray, tArray)) {
                    result = "Yes";
                    break;
                }
            } else {
                // どちらか一方が#がない場合はそこで終わり
                if (Arrays.equals(sLeftAbove, NOTHING_LEFT_ABOVE) || Arrays.equals(tLeftAbove, NOTHING_LEFT_ABOVE)) {
                    break;
                }
                // 左上を合わせる
                int[] idou = {tLeftAbove[0] - sLeftAbove[0], tLeftAbove[1] - sLeftAbove[1]};
                String[][] sArrayTransferred = transfer(sArray, idou);
                // 比較
                if (Arrays.equals(sArrayTransferred, tArray)) {
                    result = "Yes";
                    break;
                }
            }
            // sを90度回転
            sArray = rotate(sArray);
        }

        System.out.println(result);
    }

    private static int[] getLeftAbovePos(String[][] arrayInput) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arrayInput[j][i].equals("#")) {
                    int [] returnValue = {j, i};
                    return returnValue;
                }
            }
        }
        return NOTHING_LEFT_ABOVE;
    }

    private static String[][] transfer(String[][] arrayInput, int[] idou) {
        String[][] returnInput = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                returnInput[i][j] = ".";
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arrayInput[i][j].equals("#") && (i + idou[0] < n) && (j + idou[1] < n)) {
                    returnInput[i + idou[0]][j + idou[1]] = "#";
                }
            }
        }
        return returnInput;
    }

    private static String[][] rotate(String[][] arrayInput) {
        String[][] returnInput = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                returnInput[i][j] = ".";
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arrayInput[j][n - i - 1].equals("#")) {
                    returnInput[i][j] = arrayInput[j][n - i - 1];
                }
            }
        }
        return returnInput;
    }
}

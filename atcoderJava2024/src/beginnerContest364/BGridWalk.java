package beginnerContest364;

import java.util.Arrays;
import java.util.Scanner;

public class BGridWalk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer[] hwArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
        var h = hwArray[0];
        var w = hwArray[1];
        Integer[] sArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
        var nowI = sArray[0] - 1;
        var nowJ = sArray[1] - 1;

        String[][] cArrayArray = new String[h][w];
        for (int i = 0; i < h; i++) {
            var temp = sc.nextLine();
            cArrayArray[i] = temp.split("");
        }

        String x = sc.nextLine();
        for (char xChar : x.toCharArray()) {
            var xStr = String.valueOf(xChar);
            if (xStr.equals("L")) {
                if (nowJ != 0 && cArrayArray[nowI][nowJ - 1].equals(".")) {
                    nowJ = nowJ - 1;
                }
            }
            if (xStr.equals("R")) {
                if (nowJ != w - 1 && cArrayArray[nowI][nowJ + 1].equals(".")) {
                    nowJ = nowJ + 1;
                }
            }
            if (xStr.equals("U")) {
                if (nowI != 0 && cArrayArray[nowI - 1][nowJ].equals(".")) {
                    nowI = nowI - 1;
                }
            }
            if (xStr.equals("D")) {
                if (nowI != h - 1 && cArrayArray[nowI + 1][nowJ].equals(".")) {
                    nowI = nowI + 1;
                }
            }
        }
        System.out.println((nowI + 1) + " " + (nowJ + 1));
    }
}

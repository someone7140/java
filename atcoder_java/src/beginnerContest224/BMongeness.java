package beginnerContest224;

import java.util.Scanner;

public class BMongeness {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int[][] hwArray = new int[h][w];

        for (int i = 0; i < h; i++) {
            int[] wArray = new int[w];
            for (int j = 0; j < w; j++) {
                wArray[j] = sc.nextInt();
            }
            hwArray[i] = wArray;
        }

        String result = "Yes";
        for (int i1 = 0; i1 < h - 1; i1++) {
            for (int i2 = i1; i2 < h; i2++) {
                for (int j1 = 0; j1 < w - 1; j1++) {
                    for (int j2 = j1; j2 < w; j2++) {
                        long before = hwArray[i1][j1] + hwArray[i2][j2];
                        long after = hwArray[i2][j1] + hwArray[i1][j2];
                        if (before > after) {
                            result = "No";
                        }
                    }
                }
            }
            if (result.equals("No")) {
                break;
            }
        }

        System.out.println(result);
    }
}

package beginnerContest187;

import java.util.Scanner;

public class BGentlePairs {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = 0;
        int[][] xyArray = new int[n][2];
        for (int i = 0; i < n; i++) {
            xyArray[i][0] = sc.nextInt();
            xyArray[i][1] = sc.nextInt();
        }

        for(int i = 0; i < n - 1; i++) {
            int x1 = xyArray[i][0];
            int y1 = xyArray[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = xyArray[j][0];
                int y2 = xyArray[j][1];

                if (x2 != x1) {
                    int wari = Math.abs((y2 - y1)/(x2 - x1));
                    int amari = Math.abs((y2 - y1)%(x2 - x1));
                    if (wari == 0 || (wari == 1 && amari == 0)) {
                        result++;
                    }
                }

            }
        }
        System.out.println(result);
    }

}

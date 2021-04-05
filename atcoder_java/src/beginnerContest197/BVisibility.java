package beginnerContest197;

import java.util.Scanner;

public class BVisibility {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.nextLine();
        String[][] sArray = new String[h][w];
        for (int i = 0; i < h; i++) {
            String s = sc.nextLine();
            char[] sCharArray = s.toCharArray();
            for(int j = 0; j < sCharArray.length; j++) {
                sArray[i][j] = String.valueOf(sCharArray[j]);
            }
        }
        int result = 1;
        // 上
        int aboveX = x - 2;
        while(true) {
            if (aboveX > -1 && ".".equals(sArray[aboveX][y-1])) {
                result++;
                aboveX--;
            } else {
                break;
            }
        }
        //　下
        int downX = x;
        while(true) {
            if (downX < h && ".".equals(sArray[downX][y-1])) {
                result++;
                downX++;
            } else {
                break;
            }
        }
        // 左
        int leftY = y - 2;
        while(true) {
            if (leftY > -1 && ".".equals(sArray[x-1][leftY])) {
                result++;
                leftY--;
            } else {
                break;
            }
        }
        //　右
        int rightY = y;
        while(true) {
            if (rightY < w && ".".equals(sArray[x-1][rightY])) {
                result++;
                rightY++;
            } else {
                break;
            }
        }
        System.out.println(result);
    }
}

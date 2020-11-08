package beginnerContest182;

import java.util.Scanner;

public class EAkari {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();

        String[][] squareArray = new String[w][h];

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            squareArray[a][b] = "hikari";
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            squareArray[a][b] = "block";
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                String target = squareArray[i][j];
                if ("hikari".equals(target) || "block".equals(target) ) {
                    //　何もしない
                } else {
                    boolean findFlg = false;
                    // 上方向
                    int tempI = i;
                    int tempJ = j;
                    while(tempJ >= 0) {
                        String tempS = squareArray[tempI][tempJ];
                        if ("block".equals(tempS)) {
                            break;
                        }
                        if ("hikari".equals(tempS)) {
                            squareArray[i][j] = "light";
                            findFlg = true;
                            break;
                        }
                        tempJ--;
                    }
                    // 下方向
                    if (!findFlg) {
                        tempI = i;
                        tempJ = j;
                        while(tempJ <= h - 1) {
                            String tempS = squareArray[tempI][tempJ];
                            if ("block".equals(tempS)) {
                                break;
                            }
                            if ("hikari".equals(tempS)) {
                                squareArray[i][j] = "light";
                                findFlg = true;
                                break;
                            }
                            tempJ++;
                        }

                    }

                    // 左方向
                    if (!findFlg) {
                        tempI = i;
                        tempJ = j;
                        while(tempI >= 0) {
                            String tempS = squareArray[tempI][tempJ];
                            if ("block".equals(tempS)) {
                                break;
                            }
                            if ("hikari".equals(tempS)) {
                                squareArray[i][j] = "light";
                                findFlg = true;
                                break;
                            }
                            tempI--;
                        }

                    }

                    // 右方向
                    if (!findFlg) {
                        tempI = i;
                        tempJ = j;
                        while(tempI <= w - 1) {
                            String tempS = squareArray[tempI][tempJ];
                            if ("block".equals(tempS)) {
                                break;
                            }
                            if ("hikari".equals(tempS)) {
                                squareArray[i][j] = "light";
                                findFlg = true;
                                break;
                            }
                            tempI++;
                        }
                    }

                }
            }
        }

        long result = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if ("hikari".equals(squareArray[i][j]) || "light".equals(squareArray[i][j]) ) {
                    result++;
                }
            }
        }
        System.out.println(result);

    }

}

package beginnerContest359;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;

public class CTileDistance2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long[] sArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Long.parseLong(a)).toArray(Long[]::new);
        long sx = sArray[0];
        long sy = sArray[1];
        Long[] tArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Long.parseLong(a)).toArray(Long[]::new);
        long tx = tArray[0];
        long ty = tArray[1];

        long result = 0;
        // まずは縦の比較
        long tateSabun = abs(sy - ty);

        // スタートの横の初期値を決める
        long initialX = 0;
        if (sx == tx) {
            initialX = sx;
        } else if (sx < tx) {
            if (sy % 2 == 0) {
                if (sx % 2 == 0) {
                    initialX = sx + 1;
                } else {
                    initialX = sx;
                }
            } else {
                if (sx % 2 == 0) {
                    initialX = sx;
                } else {
                    initialX = sx + 1;
                }
            }
        } else {
            if (sy % 2 == 0) {
                if (sx % 2 == 0) {
                    initialX = sx;
                } else {
                    initialX = sx - 1;
                }
            } else {
                if (sx % 2 == 0) {
                    initialX = sx - 1;
                } else {
                    initialX = sx;
                }
            }
        }
        // エンドの横の初期値を決める
        long endX = 0;
        if (initialX == tx) {
            endX =tx;
        } else if (initialX > tx) {
            if (ty % 2 == 0) {
                if (tx % 2 == 0) {
                    endX = tx + 1;
                } else {
                    endX = tx;
                }
            } else {
                if (tx % 2 == 0) {
                    endX = tx;
                } else {
                    endX = tx + 1;
                }
            }
        } else {
            if (ty % 2 == 0) {
                if (tx % 2 == 0) {
                    endX = tx;
                } else {
                    endX = tx - 1;
                }
            } else {
                if (tx % 2 == 0) {
                    endX = tx - 1;
                } else {
                    endX = tx;
                }
            }
        }
        long yokoSabun = abs(initialX - endX);

        if (yokoSabun <= tateSabun) {
            result = tateSabun;
        } else {
            long sabun = yokoSabun - tateSabun;
            long sa = sabun / 2;
            long amari = sabun % 2;
            result = tateSabun + sa + amari;
        }

        System.out.println(result);
    }
}

package beginnerContest305;

import java.util.*;


public class CSnuketheCookiePicker {
    static int h;
    static int w;
    static String[][] sArrayArray;
    static Boolean[][] sArrayArrayVisited;

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        h = sc.nextInt();
        w = sc.nextInt();
        sc.nextLine();

        sArrayArray = new String[h][w];
        sArrayArrayVisited = new Boolean[h][w];
        for (int i = 0; i < h; i++) {
            sArrayArray[i] = sc.nextLine().split("");
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.add("0-0");

        Integer resultH = null;
        Integer resultW = null;

        while (!queue.isEmpty() && resultH == null && resultW == null) {
            var zahyou = queue.poll();
            var zahyouStrs = zahyou.split("-");
            var zahyouH = Integer.parseInt(zahyouStrs[0]);
            var zahyouW = Integer.parseInt(zahyouStrs[1]);
            if (sArrayArrayVisited[zahyouH][zahyouW] == null || !sArrayArrayVisited[zahyouH][zahyouW]) {
                sArrayArrayVisited[zahyouH][zahyouW] = true;
                // クッキーがある場合（左上と判断）
                if (sArrayArray[zahyouH][zahyouW].equals("#")) {
                    var rightEnd = zahyouW;
                    var lowerEnd = zahyouH;

                    // まず右の端を判定
                    while (true) {
                        if (sArrayArray[zahyouH][rightEnd].equals("#")) {
                            rightEnd = rightEnd + 1;
                        } else {
                            if (rightEnd + 1 == w) {
                                if (zahyouH < h - 1) {
                                    if (sArrayArray[zahyouH + 1][rightEnd].equals("#")) {
                                        resultW = rightEnd;
                                        resultH = zahyouH;
                                        break;
                                    }
                                }
                                rightEnd = rightEnd - 1;
                                break;
                            }
                            if (sArrayArray[zahyouH][rightEnd + 1].equals("#")) {
                                resultW = rightEnd;
                                resultH = zahyouH;
                                break;
                            } else {
                                rightEnd = rightEnd - 1;
                                break;
                            }
                        }

                        if (rightEnd == w) {
                            rightEnd = rightEnd - 1;
                            break;
                        }
                    }

                    if (resultW != null || resultH != null) {
                        continue;
                    }

                    // 下の端を判定
                    while (true) {
                        if (sArrayArray[lowerEnd][zahyouW].equals("#")) {
                            lowerEnd = lowerEnd + 1;
                        } else {
                            if (lowerEnd + 1 == h) {
                                if (sArrayArray[lowerEnd][zahyouW + 1].equals("#")) {
                                    resultW = zahyouW;
                                    resultH = lowerEnd;
                                    break;
                                }
                                lowerEnd = lowerEnd - 1;
                                break;
                            }
                            if (sArrayArray[lowerEnd + 1][zahyouW].equals("#")) {
                                resultW = zahyouW;
                                resultH = lowerEnd;
                                break;
                            } else {
                                lowerEnd = lowerEnd - 1;
                                break;
                            }
                        }

                        if (lowerEnd == h) {
                            lowerEnd = lowerEnd - 1;
                            break;
                        }
                    }

                    if (resultW != null || resultH != null) {
                        continue;
                    }

                    // 範囲の全マスを判定
                    for (int i = zahyouH; i <= lowerEnd; i++) {
                        for (int j = zahyouW; j <= rightEnd; j++) {
                            sArrayArrayVisited[i][j] = true;
                            if (sArrayArray[i][j].equals(".")) {
                                resultH = i;
                                resultW = j;
                            }
                        }
                    }
                } else {
                    // 上に進む
                    if (zahyouH > 0 &&
                            (sArrayArrayVisited[zahyouH - 1][zahyouW] == null ||
                                    !sArrayArrayVisited[zahyouH - 1][zahyouW])) {
                        queue.add(String.format("%d-%d", zahyouH - 1, zahyouW));
                    }
                    // 下に進む
                    if (zahyouH < h - 1 &&
                            (sArrayArrayVisited[zahyouH + 1][zahyouW] == null ||
                                    !sArrayArrayVisited[zahyouH + 1][zahyouW])) {
                        queue.add(String.format("%d-%d", zahyouH + 1, zahyouW));
                    }
                    // 左に進む
                    if (zahyouW > 0 &&
                            (sArrayArrayVisited[zahyouH][zahyouW - 1] == null ||
                                    !sArrayArrayVisited[zahyouH][zahyouW - 1])) {
                        queue.add(String.format("%d-%d", zahyouH, zahyouW - 1));
                    }
                    // 右に進む
                    if (zahyouW < w - 1 &&
                            (sArrayArrayVisited[zahyouH][zahyouW + 1] == null ||
                                    !sArrayArrayVisited[zahyouH][zahyouW + 1])) {
                        queue.add(String.format("%d-%d", zahyouH, zahyouW + 1));
                    }
                }

            }
        }

        System.out.println(String.format("%d %d", resultH + 1, resultW + 1));
    }
}
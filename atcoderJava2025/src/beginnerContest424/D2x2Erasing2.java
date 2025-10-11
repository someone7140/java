package beginnerContest424;

import java.util.*;

public class D2x2Erasing2 {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var t = sc.nextInt();
        sc.nextLine();

        var resultList = new ArrayList<String>();
        for (int i = 0; i < t; i++) {
            var queries = sc.nextLine().split(" ");
            var h = Integer.parseInt(queries[0]);
            var w = Integer.parseInt(queries[1]);

            var sLinesLines = new String[h][w];
            for (int j = 0; j < h; j++) {
                var sLines = sc.nextLine().split("");
                sLinesLines[j] = sLines;
            }

            var tempResult = 0;
            Queue<String> queue = new ArrayDeque<>();
            queue.add("0-0");

            while (!queue.isEmpty()) {
                var zahyou = queue.poll().split("-");
                var hPos = Integer.parseInt(zahyou[0]);
                var wPos = Integer.parseInt(zahyou[1]);
                if (hPos < h - 1 && wPos < w - 1) {
                    var hPos2 = hPos;
                    var wPos2 = wPos + 1;
                    var hPos3 = hPos + 1;
                    var wPos3 = wPos;
                    var hPos4 = hPos + 1;
                    var wPos4 = wPos + 1;
                    if (sLinesLines[hPos][wPos].equals("#")
                            && sLinesLines[hPos2][wPos2].equals("#") &&
                            sLinesLines[hPos3][wPos3].equals("#") &&
                            sLinesLines[hPos4][wPos4].equals("#")) {
                        sLinesLines[hPos4][wPos4] = ".";
                        tempResult = tempResult + 1;
                    }
                    queue.add(hPos2 + "-" + wPos2);
                    queue.add(hPos3 + "-" + wPos3);
                }
            }

            resultList.add(String.valueOf(tempResult));
        }

        System.out.println(String.join("\n", resultList));
    }
}

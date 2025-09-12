package beginnerContest415;

import java.util.*;

public class CMixture {
    static Queue<Integer> bitQueue;
    
    public static boolean loop(int num, Integer[] sArray) {
        var size = bitQueue.size();
        if (size == 0) {
            return true;
        }

        for (int i = 0; i < size; i++) {
            var bit = bitQueue.poll();
            var tempNum = (int)(num - Math.pow(2, bit));
            if (tempNum < 1) {
                return true;
            }

            if (sArray[tempNum - 1] == 0) {
                var tempResult = loop(tempNum, sArray);
                if (tempResult) {
                    return true;
                }
            }
            // 戻す
            bitQueue.add(bit);
        }

        return false;
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var t = sc.nextInt();
        var resultList = new ArrayList<String>();
        sc.nextLine();

        for (int i = 0; i < t; i++) {
            var n = Integer.parseInt(sc.nextLine());
            var sArray = Arrays.stream(sc.nextLine().split(""))
                    .map(a -> Integer.parseInt(a))
                    .toArray(Integer[]::new);
            // 全てビットを立てる状態
            StringBuilder sb = new StringBuilder("");
            bitQueue = new ArrayDeque<>();
            for (int j = 0; j < n; j++) {
                sb.append("1");
                bitQueue.add(j);
            }
            // 一旦判定
            int num = Integer.parseInt(sb.toString(), 2);
            if (sArray[num - 1] == 0) {
                var result = loop(num, sArray);
                resultList.add(result ? "Yes" : "No");
            } else {
                resultList.add("No");
            }
        }

        System.out.println(String.join("\n", resultList));
    }
}

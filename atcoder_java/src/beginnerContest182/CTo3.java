package beginnerContest182;

import java.util.*;

public class CTo3 {
    static List<Integer> resultList = new ArrayList();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        String nStr = String.valueOf(n);
        char[] nCharArray = nStr.toCharArray();
        int[] nArray = new int[nStr.length()];
        for(int i = 0; i < nStr.length(); i++) {
            nArray[i] = Integer.parseInt(String.valueOf(nCharArray[i]));
        }
        resultJudge(nArray, 0, "");
        if (resultList.size() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(resultList.stream().mapToInt(v -> v).min().orElse(-1));
        }
    }

    private static void resultJudge(int[] nArray, int count, String n) {
        if (nArray.length == 1) {
            // 含めるケース
            String temp = n + String.valueOf(nArray[0]);
            if (Long.parseLong(temp) % 3 == 0) {
                resultList.add(count);
            }
            // 含めないケース
            if (n.length() > 0 && Long.parseLong(n) % 3 == 0) {
                resultList.add(count + 1);
            }
        } else {
            // 含めるケース
            String includeN = n + String.valueOf(nArray[0]);
            int[] nextArray = Arrays.copyOfRange(nArray, 1, nArray.length);
            resultJudge(nextArray, count, includeN);
            // 含めないケース
            resultJudge(nextArray, count + 1, n);
        }
    }
}

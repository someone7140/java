package beginnerContest204;

import java.util.*;

public class CTour {
    static long[] resultArray;
    static int[][] mArray;
    static Map<String, Integer> mEvaluetedCount = new HashMap<>();
    static Map<Integer, Set<Integer>> mMap= new HashMap<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        resultArray = new long[n];
        mArray = new int[m][2];
        Arrays.fill(resultArray, 1);

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            mArray[i][0] = a;
            mArray[i][1] = b;
            Set<Integer> getAList = mMap.get(a);
            if (getAList != null) {
                getAList.add(b);
            } else {
                Set<Integer> set = new HashSet<>(Arrays.asList(b));
                mMap.put(a, set);
            }
        }

        for (int i = 0; i < m; i++) {
            int a = mArray[i][0];
            int b = mArray[i][1];
            Integer mEvalueted = mEvaluetedCount.get(a + "-" + b);
            if (mEvalueted == null) {
                getNextCount(0, 0);
            }
        }

        System.out.println(Arrays.stream(resultArray).sum());
    }

    private static Long getNextCount(int nextA, long count) {
        Set<Integer> nextSet = mMap.get(nextA);
        for(Integer b : nextSet) {
            Integer mEvalueted = mEvaluetedCount.get(nextA + "-" + b);
            if (mEvalueted == null) {
                mEvaluetedCount.put(nextA + "-" + b, (int)count);
                long tempResult = getNextCount(b, count + 1);
                resultArray[nextA] = resultArray[nextA] + tempResult;
            } else {
                 // result = count - mEvalueted;
            }
        };
        return resultArray[nextA];
    }
}

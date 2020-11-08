package regularContest106;

import java.util.*;

public class BValues {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] aArray = new long[n];
        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextLong();
        }
        long[] bArray = new long[n];
        for (int i = 0; i < n; i++) {
            bArray[i] = sc.nextLong();
        }
        Set<Integer> cdSet = new HashSet();
        for (int i = 0; i < m; i++) {
            cdSet.add(sc.nextInt());
            cdSet.add(sc.nextInt());
        }
        String result = "Yes";
        long sabunSum = 0;
        for (int i = 1; i <= n; i++) {
            boolean include = cdSet.contains(i);
            if (!include) {
                if (aArray[i - 1] != bArray[i - 1]) {
                    result = "No";
                    break;
                }
            } else {
                long a = aArray[i - 1];
                long b = bArray[i - 1];
                sabunSum = sabunSum + (a - b);
            }
        }
        if (result.equals("Yes")) {
            if(sabunSum == 0) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else {
            System.out.println(result);
        }
    }

}

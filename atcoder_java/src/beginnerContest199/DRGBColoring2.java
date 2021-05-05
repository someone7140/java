package beginnerContest199;

import java.util.Arrays;
import java.util.Scanner;

public class DRGBColoring2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] nArray = new long[n];
        Arrays.fill(nArray, 3L);
        boolean[] nFlagArray = new boolean[n];
        Arrays.fill(nFlagArray, false);
        long result = 1L;

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            if(!nFlagArray[b]) {
                nArray[b] = nArray[a] - 1;
                if (nArray[b] < 0) {
                    nArray[b] = 0;
                }
            }
            nFlagArray[a] = true;
            nFlagArray[b] = true;
        }


        for (int i = 0; i < n; i++) {
            result = result * nArray[i];
        }
        System.out.println(result);

    }

}

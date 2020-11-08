package beginnerContest182;

import java.util.Arrays;
import java.util.Scanner;

public class BAlmostGCD {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] aArray = new int[n];
        for(int i = 0; i < n; i++) {
            aArray[i] = sc.nextInt();
        }
        long maxCount = 0;
        int result = 0;
        int max = Arrays.stream(aArray).max().orElse(0);

        for(int i = 2; i <= max; i++) {
            final int i2 = i;
            long count = Arrays.stream(aArray).filter(a -> a % i2 == 0).count();
            if(count > maxCount) {
                maxCount = count;
                result = i;
            }
        }

        System.out.println(result);
    }
}

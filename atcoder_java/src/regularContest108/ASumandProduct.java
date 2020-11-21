package regularContest108;

import java.util.Scanner;

public class ASumandProduct {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long s = sc.nextLong();
        long p = sc.nextLong();

        long pSqrt = (long)Math.sqrt(p) + 10;
        String result = "No";
        for(long i = 1; i <= pSqrt; i++) {
            if (p % i == 0) {
                long warizan = p / i;
                long tashizan = i + warizan;
                if (tashizan == s) {
                    result = "Yes";
                    break;
                }
            }
        }
        System.out.println(result);
    }
}

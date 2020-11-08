package beginnerContest181;

import java.util.Scanner;

public class BTrapezoidSum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long result = 0L;
        for (int i = 0; i < n; i++) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long aSum = (a - 1) * (a) / 2L;
            long bSum = b * (b + 1) / 2L;
            result = result + bSum - aSum;
        }
        System.out.println(result);
    }
}

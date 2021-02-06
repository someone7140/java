package beginnerContest189;

import java.util.Scanner;

public class BAlcoholic {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long x = sc.nextLong() * 100;
        double tempA = 0;
        int result = -1;
        for (int i = 0; i < n; i++) {
            long v = sc.nextLong();
            long p = sc.nextLong();
            if (result == -1) {
                long vp = v * p;
                tempA = tempA + vp;
                if (tempA > x) {
                    result = i + 1;
                }
            }
        }
        System.out.println(result);
    }
}

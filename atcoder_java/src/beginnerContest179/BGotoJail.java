package beginnerContest179;

import java.util.Scanner;

public class BGotoJail {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String result = "No";
        int zoroCount = 0;
        for (int i = 0; i < n; i++) {
            int d1 = sc.nextInt();
            int d2 = sc.nextInt();
            if (d1 == d2) {
                zoroCount++;
            } else {
                zoroCount = 0;
            }
            if (zoroCount >= 3) {
                result = "Yes";
            }

        }
        System.out.println(result);

    }
}

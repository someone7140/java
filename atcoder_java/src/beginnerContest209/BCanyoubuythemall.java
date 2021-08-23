package beginnerContest209;

import java.util.Scanner;

public class BCanyoubuythemall {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long x = sc.nextLong();
        long total = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                total = total + sc.nextLong();
            } else {
                total = total + sc.nextLong() - 1;
            }

        }
        if (x >= total) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

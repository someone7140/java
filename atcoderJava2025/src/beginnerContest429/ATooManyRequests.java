package beginnerContest429;

import java.util.Scanner;

public class ATooManyRequests {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            if (i < m) {
                System.out.println("OK");
            } else {
                System.out.println("Too Many Requests");
            }
        }
    }
}

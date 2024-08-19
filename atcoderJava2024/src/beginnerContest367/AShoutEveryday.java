package beginnerContest367;

import java.util.Scanner;

public class AShoutEveryday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        if (a < b) {
            if (a < c && b > c) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
        } else {
            if (a < c) {
                System.out.println("No");
            } else if (c < b) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
        }
    }
}

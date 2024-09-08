package beginnerContest370;

import java.util.Scanner;

public class ARaiseBothHands {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int r = sc.nextInt();

        if (l == 1 && r == 0) {
            System.out.println("Yes");
        } else if (l == 0 && r == 1) {
            System.out.println("No");
        } else {
            System.out.println("Invalid");
        }
    }
}

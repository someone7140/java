package beginnerContest365;

import java.util.Scanner;

public class ALeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n % 4 != 0) {
            System.out.println(365);
        } else if (n % 100 != 0) {
            System.out.println(366);
        } else if (n % 400 != 0) {
            System.out.println(365);
        } else {
            System.out.println(366);
        }

    }
}

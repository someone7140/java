package beginnerContest418;

import java.util.Scanner;

public class AImateapot {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        sc.nextLine();
        var s = sc.nextLine();

        System.out.println(s.endsWith("tea") ? "Yes" : "No");
    }
}

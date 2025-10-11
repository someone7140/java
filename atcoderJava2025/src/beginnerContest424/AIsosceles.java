package beginnerContest424;

import java.util.Scanner;

public class AIsosceles {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var a = sc.nextInt();
        var b = sc.nextInt();
        var c = sc.nextInt();

        if (a == b || a == c || b == c) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }
}

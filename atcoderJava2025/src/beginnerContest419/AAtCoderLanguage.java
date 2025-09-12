package beginnerContest419;

import java.util.Scanner;

public class AAtCoderLanguage {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var s = sc.nextLine();
        if (s.equals("red")) {
            System.out.println("SSS");
        } else if (s.equals("blue")) {
            System.out.println("FFF");
        } else if (s.equals("green")) {
            System.out.println("MMM");
        } else {
            System.out.println("Unknown");
        }

    }
}

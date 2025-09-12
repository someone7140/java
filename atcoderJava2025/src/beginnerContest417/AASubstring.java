package beginnerContest417;

import java.util.Scanner;

public class AASubstring {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var a = sc.nextInt();
        var b = sc.nextInt();
        sc.nextLine();

        var s = sc.nextLine();
        if (a > 0) {
            s = s.substring(a);
        }
        if (b > 0) {
            s = s.substring(0, s.length() - b);
        }
        System.out.println(s);
    }
}

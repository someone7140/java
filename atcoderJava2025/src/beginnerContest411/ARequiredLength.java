package beginnerContest411;

import java.util.Scanner;

public class ARequiredLength {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var p = sc.nextLine();
        var l = Integer.parseInt(sc.nextLine());

        var result = p.length() >= l ? "Yes" : "No";
        System.out.println(result);
    }
}

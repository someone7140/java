package beginnerContest416;

import java.util.Scanner;

public class AVacationValidation {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var l = sc.nextInt();
        var r = sc.nextInt();
        sc.nextLine();

        var sArray = sc.nextLine().split("");
        var result = "Yes";
        for (int i = 0; i < n; i++) {
            if (i >= l - 1 && i < r) {
                if (!sArray[i].equals("o")) {
                    result = "No";
                    break;
                }
            }
        }
        System.out.println(result);
    }
}

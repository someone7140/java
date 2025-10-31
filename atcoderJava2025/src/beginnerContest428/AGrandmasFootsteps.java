package beginnerContest428;

import java.util.Scanner;

public class AGrandmasFootsteps {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var s = sc.nextInt();
        var a = sc.nextInt();
        var b = sc.nextInt();
        var x = sc.nextInt();

        var tempCount = 0;
        var walkMode = true;
        var result = 0;
        for (int i = 0; i < x; i++) {
            if (walkMode) {
                if (tempCount == a) {
                    tempCount = 0;
                    walkMode = false;
                }
            } else {
                if (tempCount == b) {
                    tempCount = 0;
                    walkMode = true;
                }
            }

            if (walkMode) {
                result = result + s;
            }
            tempCount = tempCount + 1;
        }
        System.out.println(result);
    }
}

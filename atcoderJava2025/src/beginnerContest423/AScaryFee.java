package beginnerContest423;

import java.util.Scanner;

public class AScaryFee {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();
        var c = sc.nextDouble();

        var riritsu = c / 1000;
        var tempN = (int) (n / (1 + riritsu));

        if (tempN < 1000) {
            System.out.println(0);
            return;
        }

        tempN = tempN / 1000 * 1000;
        System.out.println(tempN);
    }
}

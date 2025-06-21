package beginnerContest305;

import java.util.Scanner;

public class AWaterStation {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var n = sc.nextInt();

        if (n % 5 == 0) {
            System.out.println(n);
            return;
        }

        var resultPlus = 0;
        var resultMinus = 0;
        while (true) {
            resultPlus = resultPlus + 1;
            if ((n + resultPlus) % 5 == 0) {
                break;
            }
        }
        while (true) {
            resultMinus = resultMinus + 1;
            if ((n - resultMinus) % 5 == 0) {
                break;
            }
        }

        System.out.println(resultPlus > resultMinus ? n - resultMinus : n + resultPlus);
    }
}

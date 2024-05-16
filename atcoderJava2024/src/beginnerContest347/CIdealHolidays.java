package beginnerContest347;

import beginnerContest331.CSumofNumbersGreaterThanMe;

import java.util.Arrays;
import java.util.Scanner;

public class CIdealHolidays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.nextLine();
        String dStr = sc.nextLine();
        Integer[] dArray = Arrays.stream(dStr.split(" ")).map(d -> Integer.parseInt(d)).toArray(Integer[]::new);
        Integer[] dArrayPlus = new Integer[n];

        for (int i = 0; i < n; i++) {
            dArrayPlus[i] = dArray[n - 1] + dArray[i];
        }
        Integer[] dArrayAll = Arrays.copyOf(dArray, 2 * n);
        System.arraycopy(dArrayPlus, 0, dArrayAll, n, n);

        String result = "No";
        int tempDay = 0;
        for (int i = 0; i < n; i++) {
            tempDay = 1;
            boolean tempResult = true;
            int before = dArrayAll[i];
            for (int j = i + 1; j < 2*n-1; j++) {
                tempDay = tempDay + (dArrayAll[j] - before);
                if (tempDay > a + b) {
                    tempDay = tempDay - (a + b);
                }

                if (tempDay > a) {
                    tempResult = false;
                    break;
                }
            }
            if (tempResult) {
                result = "Yes";
                break;
            }

        }

        System.out.println(result);
    }
}

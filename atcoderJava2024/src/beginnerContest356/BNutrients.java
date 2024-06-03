package beginnerContest356;

import java.util.Arrays;
import java.util.Scanner;

public class BNutrients {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        String mokuhyouStr = sc.nextLine();
        Integer[] mokuhyouArray = Arrays.stream(mokuhyouStr.split(" ")).map(mokuhyou -> Integer.parseInt(mokuhyou)).toArray(Integer[]::new);
        int[] xArray = new int[m];

        for (int i = 0; i < n; i++) {
            String xStr = sc.nextLine();
            Integer[] xArrayTemp = Arrays.stream(xStr.split(" ")).map(x -> Integer.parseInt(x)).toArray(Integer[]::new);
            for (int j = 0; j < m; j++) {
                xArray[j] = xArray[j] + xArrayTemp[j];
            }
        }

        String result = "Yes";
        for (int i = 0; i < m; i++) {
            if (mokuhyouArray[i] > xArray[i]) {
                result = "No";
                break;
            }
        }

        System.out.println(result);
    }
}

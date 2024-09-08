package beginnerContest370;

import java.util.Arrays;
import java.util.Scanner;

public class BBinaryAlchemy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] aArrayArray = new int[n][n];

        for (int i = 0; i < n; i++) {
            var aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);
            for (int j = 0; j < i + 1; j++) {
                aArrayArray[i][j] = aArray[j];
            }
        }

        var result = aArrayArray[0][0];
        for (int i = 1; i < n; i++) {
            if (result - 1 >= i) {
                result = aArrayArray[result - 1][i];
            } else {
                result = aArrayArray[i][result - 1];
            }

        }

        System.out.println(result);
    }
}

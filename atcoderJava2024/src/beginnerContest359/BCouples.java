package beginnerContest359;

import java.util.Arrays;
import java.util.Scanner;

public class BCouples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Integer[] aArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);

        int result = 0;
        for (int i = 0; i < 2 * n - 2; i++) {
            int a1 = aArray[i];
            int a2 = aArray[i + 1];
            int a3 = aArray[i + 2];
            if (a1 == a3 && a1 != a2) {
                result = result + 1;
            }
        }
        System.out.println(result);
    }
}

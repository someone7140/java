package beginnerContest317;

import java.util.Arrays;
import java.util.Scanner;

public class APotions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int h = sc.nextInt();
        int x = sc.nextInt();
        sc.nextLine();
        Integer[] pArray = Arrays.stream(sc.nextLine().split(" ")).map(a -> Integer.parseInt(a)).toArray(Integer[]::new);

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (pArray[i] + h >= x) {
                result = i + 1;
                break;
            }
        }

        System.out.println(result);
    }
}

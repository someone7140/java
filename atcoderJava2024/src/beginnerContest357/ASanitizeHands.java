package beginnerContest357;

import java.util.Arrays;
import java.util.Scanner;

public class ASanitizeHands {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        Integer[] hArray = Arrays.stream(sc.nextLine().split(" ")).map(h -> Integer.parseInt(h)).toArray(Integer[]::new);

        int result = 0;
        for (int i = 0; i < n; i++) {
            m = m - hArray[i];
            if (m >= 0) {
                result = result + 1;
            }
        }

        System.out.println(result);
    }
}

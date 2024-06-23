package beginnerContest359;

import java.util.Scanner;

public class ACountTakahashi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int result = 0;
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            if (s.equals("Takahashi")) {
                result = result + 1;
            }
        }
        System.out.println(result);
    }
}

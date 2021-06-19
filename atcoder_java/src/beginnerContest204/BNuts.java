package beginnerContest204;

import java.util.Scanner;

public class BNuts {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int result = 0;
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            if (a > 10) {
                result = result + a - 10;
            }
        }
        System.out.println(result);
    }
}

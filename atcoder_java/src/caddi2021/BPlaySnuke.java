package caddi2021;

import java.util.Scanner;

public class BPlaySnuke {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = -1;
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int p = sc.nextInt();
            int x = sc.nextInt();
            if (x - a > 0) {
                if (result == -1) {
                    result = p;
                    continue;
                }
                if (result > p) {
                    result = p;
                }
            }
        }

        System.out.println(result);
    }
}

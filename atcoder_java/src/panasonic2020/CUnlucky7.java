package panasonic2020;

import java.util.Scanner;

public class CUnlucky7 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (String.valueOf(i).contains("7")) {
                // 何もしない
            } else if (Integer.toOctalString(i).contains("7")) {
                // 何もしない
            } else {
                result++;
            }
        }
        System.out.println(result);
    }
}

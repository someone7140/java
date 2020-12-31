package beginnerContest185;

import java.util.Scanner;

public class BSmartphoneAddiction {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int m = sc.nextInt();
        long t = sc.nextLong();

        long nowTime = 0;
        long battery = n;
        long a = 0;
        long b = 0;
        String result = "Yes";
        for (int i = 0; i < m; i++) {
            a = sc.nextLong();
            b = sc.nextLong();
            battery = battery - (a - nowTime);
            if (battery <= 0) {
                result = "No";
                break;
            }
            battery = Math.min(n, battery + (b - a));
            nowTime = b;
        }

        battery = battery - (t - b);
        if (battery <= 0) {
            result = "No";
        }

        System.out.println(result);
    }
}

package kyocera2021;

import java.util.Scanner;

public class B200thABC200 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int k = sc.nextInt();
        long result = n;

        for (int i = 0; i < k; i++) {
            if (result % 200 == 0) {
                result = result / 200;
            } else {
                String tempStr = String.valueOf(result) + "200";
                result = Long.parseLong(tempStr);
            }
        }
        System.out.println(result);
    }
}

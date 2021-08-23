package beginnerContest206;

import java.util.Scanner;

public class BSavings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long sum = 0;
        long result = 0;
        while (true) {
            result++;
            sum = sum + result;
            if (sum >= n) {
               break;
            }
        }
        System.out.println(result);
    }
}

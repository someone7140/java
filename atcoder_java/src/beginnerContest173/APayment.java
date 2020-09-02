package beginnerContest173;

import java.util.Scanner;

public class APayment {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int temp = n / 1000 * 1000;
        int result = temp == n ? 0 : temp + 1000 - n;
        System.out.println(result);
    }
}

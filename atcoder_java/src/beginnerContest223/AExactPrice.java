package beginnerContest223;

import java.util.Scanner;

public class AExactPrice {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        if (x / 100 > 0 && x % 100 == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

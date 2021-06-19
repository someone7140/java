package beginnerContest205;

import java.util.Scanner;

public class Akcal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a = sc.nextInt();
        double b = sc.nextInt();

        double result = b / 100 * a;

        System.out.println(result);
    }
}

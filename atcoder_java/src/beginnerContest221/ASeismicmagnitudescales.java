package beginnerContest221;

import java.util.Scanner;

public class ASeismicmagnitudescales {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long aMinusB = a - b;

        System.out.println((long)Math.pow(32, aMinusB));
    }
}

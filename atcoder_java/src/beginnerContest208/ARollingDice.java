package beginnerContest208;

import java.util.Scanner;

public class ARollingDice {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        if (a * 6 < b || a > b) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}


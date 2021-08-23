package beginnerContest209;

import java.util.Scanner;

public class ACounting {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        if (a > b) {
            System.out.println(0);
        } else {
            System.out.println(b - a + 1);
        }
    }
}


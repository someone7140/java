package beginnerContest182;

import java.util.Scanner;

public class Atwiblr {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int followMax = a * 2 + 100;
        if(followMax - b >= 0) {
            System.out.println(followMax - b);
        } else {
            System.out.println(0);
        }
    }
}

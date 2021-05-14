package kyocera2021;

import java.util.Scanner;

public class ACentury {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int syou = n /100;
        int amari = n % 100;
        if (amari != 0) {
            System.out.println(syou + 1);
        } else {
            System.out.println(syou);
        }
    }
}

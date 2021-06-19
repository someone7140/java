package aising2021;

import java.util.Scanner;

public class AThreeDice {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        System.out.println((7 - a) + (7 - b) + (7 - c));
    }
}

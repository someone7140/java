package hhkb2020;

import java.util.Scanner;

public class AKeyboard {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        if (s.equals("Y")) {
            if (t.equals("a")) {
                System.out.println("A");
            } else if (t.equals("b")) {
                System.out.println("B");
            } else {
                System.out.println("C");
            }
        } else {
            System.out.println(t);
        }
    }
}

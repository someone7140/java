package unicorn2021;

import java.util.Scanner;

public class ADistinctStrings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] sCharArray = s.toCharArray();
        String ichimojime = "";
        String nimojime = "";
        String sanmojime = "";
        for (int i = 0; i < 3; i++) {
            String temp = String.valueOf(sCharArray[i]);
            if (i == 0) {
                ichimojime = temp;
            } else if (i == 1) {
                nimojime = temp;
            } else {
                sanmojime = temp;
            }
        }

        if (ichimojime.equals(nimojime) && nimojime.equals(sanmojime)) {
            System.out.println(1);
        } else if(!ichimojime.equals(nimojime) && !nimojime.equals(sanmojime) && !ichimojime.equals(sanmojime)) {
            System.out.println(6);
        } else {
            System.out.println(3);
        }
    }
}

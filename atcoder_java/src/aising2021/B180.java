package aising2021;

import java.util.Scanner;

public class B180 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String result = "";
        char[] sCharArray = s.toCharArray();
        for (int i = sCharArray.length - 1; i >= 0; i--) {
            String temp = String.valueOf(sCharArray[i]);
            if (temp.equals("6")) {
                result = result + "9";
            } else if (temp.equals("9")){
                result = result + "6";
            } else {
                result = result + temp;
            }
        }

        System.out.println(result);
    }
}

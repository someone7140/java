package sompo2021;

import java.util.Scanner;

public class BuNrEaDaBlEsTrInG {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] sCharArry = s.toCharArray();
        int length = sCharArry.length;
        boolean result = true;
        for(int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                if (Character.isUpperCase(sCharArry[i])) {
                    result = false;
                    break;
                }
            } else {
                if (!Character.isUpperCase(sCharArry[i])) {
                    result = false;
                    break;
                }
            }
        }
        if (result) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

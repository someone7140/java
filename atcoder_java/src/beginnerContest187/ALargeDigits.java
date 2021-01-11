package beginnerContest187;

import java.util.Scanner;

public class ALargeDigits {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        int resultA = 0;
        char[] aChars = String.valueOf(a).toCharArray();
        for(char aChar : aChars) {
            resultA = resultA + Integer.parseInt(String.valueOf(aChar));
        }

        int resultB = 0;
        char[] bChars = String.valueOf(b).toCharArray();
        for(char bChar : bChars) {
            resultB = resultB + Integer.parseInt(String.valueOf(bChar));
        }

        if (resultA > resultB) {
           System.out.println(resultA);
        } else {
            System.out.println(resultB);
        }

    }
}

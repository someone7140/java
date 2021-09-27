package beginnerContest220;

import java.util.Scanner;

public class BBaseK {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String ab = sc.nextLine();
        char[] aCharArray = ab.split(" ")[0].toCharArray();
        char[] bCharArray = ab.split(" ")[1].toCharArray();

        long resultA = 0;

        for(int i = 0; i < aCharArray.length; i++) {
            resultA = resultA + (long)Math.pow(k, i) * Long.parseLong(String.valueOf(aCharArray[aCharArray.length - i - 1]));
        }

        long resultB = 0;
        for(int i = 0; i < bCharArray.length; i++) {
            resultB = resultB + (long)Math.pow(k, i) * Long.parseLong(String.valueOf(bCharArray[bCharArray.length - i - 1]));
        }
        System.out.println(resultA * resultB);
    }
}

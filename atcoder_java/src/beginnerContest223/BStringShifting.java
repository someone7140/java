package beginnerContest223;

import java.util.Scanner;

public class BStringShifting {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String maxS = s;
        String minS = s;
        String tempS = s;
        int sLength = s.length();
        if (sLength > 1) {
            for (int i = 0; i < sLength; i++) {
                String cutWord = String.valueOf(tempS.charAt(0));
                tempS = tempS.substring(1) + cutWord;
                if (tempS.compareTo(maxS) > 0) {
                    maxS = tempS;
                }
                if (tempS.compareTo(minS) < 0) {
                    minS = tempS;
                }
            }
        }

        System.out.println(minS);
        System.out.println(maxS);
    }
}

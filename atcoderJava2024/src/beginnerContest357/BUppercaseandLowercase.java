package beginnerContest357;

import java.util.Scanner;

public class BUppercaseandLowercase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int koMojiCOunt = 0;
        int ohMojiCOunt = 0;
        for (char sChar : s.toCharArray()) {
            if (Character.isLowerCase(sChar)) {
                koMojiCOunt = koMojiCOunt + 1;
            } else {
                ohMojiCOunt = ohMojiCOunt + 1;
            }
        }

        String result = "";
        if (ohMojiCOunt > koMojiCOunt) {
            result = s.toUpperCase();
        } else {
            result = s.toLowerCase();
        }

        System.out.println(result);
    }
}

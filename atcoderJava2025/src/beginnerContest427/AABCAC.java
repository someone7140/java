package beginnerContest427;

import java.util.Scanner;

public class AABCAC {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var sArray = sc.nextLine().split("");
        var result = "";
        var jogaiIndex = (sArray.length + 1) / 2 - 1;

        for (int i = 0; i < sArray.length; i++) {
            if ( i != jogaiIndex) {
                result = result + sArray[i];
            }
        }
        System.out.println(result);
    }
}

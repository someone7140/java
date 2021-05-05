package beginnerContest198;

import java.util.Scanner;

public class BPalindromewithleadingzeros {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        int zeroCount = 0;
        char[] nCharArray = n.toCharArray();
        for (int i = nCharArray.length - 1; i >= 0; i--) {
            String nCharStr = String.valueOf(nCharArray[i]);
            if (nCharStr.equals("0")) {
                zeroCount++;
            } else {
                break;
            }
        }
        String kaibun = n;
        for (int i = 0; i < zeroCount; i++) {
            kaibun = "0" + kaibun;
        }
        StringBuilder kaibunBuilder = new StringBuilder(kaibun);
        String kaibunReverse = kaibunBuilder.reverse().toString();
        if (kaibunReverse.equals(kaibun)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

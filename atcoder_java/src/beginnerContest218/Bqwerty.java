package beginnerContest218;

import java.util.Scanner;

public class Bqwerty {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] pArray = new int[26];
        for (int i = 0; i < 26; i++) {
            pArray[i] = sc.nextInt();
        }
        String result = "";
        for (int i = 0; i < 26; i++) {
            char pChar = (char)(pArray[i] - 1 + 97);
            result = result + String.valueOf(pChar);
        }
        System.out.println(result);

    }
}

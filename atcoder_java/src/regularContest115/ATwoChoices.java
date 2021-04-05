package regularContest115;

import java.util.Scanner;

public class ATwoChoices {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        byte[] sArray = new byte[n];
        sc.nextLine();
        long result = 0;
        for (int i = 0; i < n; i++) {
            sArray[i] = sc.nextByte(2);
        }
        for (int i = 0; i < n - 1; i++) {
            byte s1 = sArray[i];
            for (int j = i + 1; j < n; j++) {
                byte s2 = sArray[j];
                // 論理和
                byte resultOr = (byte) (s1 | s2);
                // 論理積
                byte s1Seki = (byte) (resultOr & s1);
                int s1Count = Integer.toBinaryString(s1Seki).replace("0", "").length();
                byte s2Seki = (byte) (resultOr & s2);
                int s2Count = Integer.toBinaryString(s2Seki).replace("0", "").length();
                if (s1Count != s2Count) {
                    result++;
                }

            }
        }
        System.out.println(result);
    }
}

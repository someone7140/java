package keyence2021;

import java.util.Scanner;

public class BMexBoxes {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] ballArray = new int[300001];

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            ballArray[a]++;
        }

        long result = 0;
        int seizon = 0;
        for (int i = 0; i <= 3000000; i++) {
            int ballCount = ballArray[i];
            if (i == 0) {
                if (ballCount == 0) {
                    break;
                } else {
                    seizon = ballCount > k ? k : ballCount;
                }
            } else if (i == 3000000) {
                if (ballCount == 0) {
                    result = result + seizon * i;
                } else if (ballCount >= seizon) {
                    result = result + seizon * (i + 1);
                } else {
                    result = result + ballCount * (i + 1);
                }
            } else {
                if (ballCount == 0) {
                    result = result + seizon * i;
                    break;
                } else if (ballCount < seizon) {
                    result = result + (seizon - ballCount) * i;
                    seizon = ballCount;
                }
            }
        }
        System.out.println(result);
    }
}

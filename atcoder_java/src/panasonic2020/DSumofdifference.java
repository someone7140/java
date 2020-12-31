package panasonic2020;

import java.util.Scanner;

public class DSumofdifference {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long result = 0;
        int[] aArray = new int[n];
        long[] sumArray = new long[n];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            aArray[i] = a;
            for (int j = 0; j < i; j++) {
                long sum = sumArray[j];
                sum = sum + Math.abs(aArray[j] - a);
                sumArray[j] = sum;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            result = result + sumArray[i];
        }
        System.out.println(result);
    }
}

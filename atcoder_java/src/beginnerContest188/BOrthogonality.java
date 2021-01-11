package beginnerContest188;

import java.util.Arrays;
import java.util.Scanner;

public class BOrthogonality {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int resultArray[] = new int[n];
        for(int i = 0; i < n; i++) {
            resultArray[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++) {
            resultArray[i] = resultArray[i] * sc.nextInt();
        }
        long sum = Arrays.stream(resultArray).sum();
        if (sum == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

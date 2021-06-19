package beginnerContest205;

import java.util.Arrays;
import java.util.Scanner;

public class BPermutationCheck {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] aArray = new int[n];
        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextInt();
        }
        int[] aArraySorted = Arrays.stream(aArray).sorted().toArray();
        String result = "Yes";
        if (aArraySorted.length == 1) {
            if (aArraySorted[0] != 1) {
                result = "No";
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                if ((aArraySorted[i + 1] - aArraySorted[i]) != 1) {
                    result = "No";
                    break;
                }
            }
        }
        System.out.println(result);
    }
}

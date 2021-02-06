package beginnerContest191;

import java.util.Arrays;
import java.util.Scanner;

public class BRemoveIt {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] aArray = new int[n];

        for (int i = 0; i < n; i++) {
            aArray[i] = sc.nextInt();
        }
        int[] result = Arrays.stream(aArray).filter(a -> a != x).toArray();
        int resultSize = result.length;
        if (resultSize == 0) {
            System.out.println("");
        } else {
            String[] resultStr = new String[resultSize];
            for (int i = 0; i < resultSize; i++) {
                resultStr[i] = String.valueOf(result[i]);
            }
            System.out.println(String.join(" ", resultStr));
        }
    }
}

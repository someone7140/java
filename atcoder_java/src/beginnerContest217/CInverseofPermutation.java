package beginnerContest217;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CInverseofPermutation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] resultArray = new int[n];
        for (int i = 0; i < n; i++) {
            int p = sc.nextInt();
            resultArray[p - 1] = i + 1;
        }
        System.out.println(
                Arrays.stream(resultArray)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }
}

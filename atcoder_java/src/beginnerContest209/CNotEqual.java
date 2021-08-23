package beginnerContest209;

import java.util.Arrays;
import java.util.Scanner;

public class CNotEqual {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long result = 1;
        long[] cArray = new long[n];
        for (int i = 0; i < n; i++) {
            cArray[i] = sc.nextLong();
        }
        cArray = Arrays.stream(cArray).sorted().toArray();
        for (int i = 0; i < n; i++) {
            result = result * (cArray[i] - i)  % 1000000007L;
            if (result <= 0) {
                result = 0;
                break;
            }
        }
        System.out.println(result);
    }
}

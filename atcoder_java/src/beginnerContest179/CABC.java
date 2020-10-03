package beginnerContest179;

import java.util.Scanner;

public class CABC {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc .nextLong();
        long max = n - 1L;
        long result = 0;

        for (long i = 1L; i < n; i++) {
            long tempMax = max / i;
            result = result + tempMax;
        }

        System.out.println(result);

    }
}

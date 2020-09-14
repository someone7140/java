package beginnerContest178;

import java.util.Scanner;

public class BProductMax {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        long d = sc.nextLong();

        long result = -1000000000000000001L;
        long[] resultArray = new long[4];
        resultArray[0] = a * c;
        resultArray[1] = a * d;
        resultArray[2] = b * c;
        resultArray[3] = b * d;
        for (int i =0; i < resultArray.length; i++) {
            long temp = resultArray[i];
            if (temp > result) {
                result = temp;
            }
        }
        System.out.println(result);
    }
}

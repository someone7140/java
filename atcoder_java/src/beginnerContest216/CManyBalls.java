package beginnerContest216;

import java.util.Scanner;

public class CManyBalls {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        String resultReverse = "";
        while (n > 0) {
            if (n % 2 == 0) {
                n = n / 2;
                resultReverse = resultReverse + "B";
            } else {
                n = n - 1;
                resultReverse = resultReverse + "A";
            }
        }
        StringBuilder strb = new StringBuilder(resultReverse);
        System.out.println(strb.reverse().toString());
    }
}

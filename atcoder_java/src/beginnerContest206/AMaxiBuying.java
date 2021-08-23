package beginnerContest206;

import java.util.Scanner;

public class AMaxiBuying {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long max = 206;
        long n = sc.nextLong();
        long result = (long)(n * 1.08);
        if (result < max) {
            System.out.println("Yay!");
        } else if (result == max) {
            System.out.println("so-so");
        } else {
            System.out.println(":(");
        }
    }
}

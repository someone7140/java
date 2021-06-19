package beginnerContest204;

import java.util.Arrays;
import java.util.Scanner;

public class ARockpaperscissors {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int result = -1;
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[] all = {0, 1, 2};

        if (x == y) {
            result = x;
        } else {
            result = Arrays.stream(all).filter(a -> a != x && a != y).findFirst().getAsInt();
        }
        System.out.println(result);
    }
}

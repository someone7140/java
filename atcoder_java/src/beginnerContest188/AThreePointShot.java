package beginnerContest188;

import java.util.Scanner;

public class AThreePointShot {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        int sabun = Math.abs(x - y);
        if (sabun < 3) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

package beginnerContest191;

import java.util.Scanner;

public class AVanishingPitch {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double v = sc.nextDouble();
        double t = sc.nextDouble();
        double s = sc.nextDouble();
        double d = sc.nextDouble();

        double byou = d / v;

        if (t <= byou && s >= byou) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}

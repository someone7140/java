package beginnerContest183;

import java.util.Scanner;

public class BBilliards {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();
        System.out.println((y1 * x2 + y2 * x1) / (y2 + y1) );
    }
}
